#include "eventqueue.h"
  
 log4cpp::OstreamAppender eventqueue::app =log4cpp::OstreamAppender("HUGO",&cout);
 int eventqueue::logpriority = log4cpp::Priority::DEBUG;

     void eventqueue::configdebug(int priority) {
      log4cpp::Layout* layout =  new log4cpp::BasicLayout();
      app.setLayout(layout);
      log4cpp::Category& root = log4cpp::Category::getRoot();
      root.addAppender(app);
      logpriority=priority;
      root.info("Program started");
    }

    event::event (int id,etype type) {
        this->name=id;
        this->type=type;
        this->acknowledged=false;
    }
    
    void event::acknowledge() {
    	pthread_mutex_lock(&mutex);
    	pthread_cond_broadcast(&cond);
    	pthread_mutex_unlock(&mutex);
    }
    
    void event::waitforacknowledge() {
        mutex=(PTHREAD_MUTEX_INITIALIZER);
        cond=(PTHREAD_COND_INITIALIZER);
        pthread_mutex_lock(&mutex);
        pthread_cond_wait(&cond,&mutex);
        pthread_mutex_unlock(&mutex);
    }
    

  //log4cplus::Logger eventqueue::log (log4cplus::Logger::getInstance(name));
   
   eventqueue::eventqueue (char * id) {
        rwmutex=PTHREAD_MUTEX_INITIALIZER;
        logm=PTHREAD_MUTEX_INITIALIZER;
        cond=PTHREAD_COND_INITIALIZER;
        pthread_mutex_lock(&rwmutex);
        internal= new queue<event>();
        external= new queue<event>();
        deferred= new queue<event>();
        completed=new set<event>();
		current = NULL;
        pthread_mutex_unlock(&rwmutex);
        name=id;
        empty=true;
        log=&log4cpp::Category::getInstance(name);
        log->setPriority(log4cpp::Priority::DEBUG);
        pthread_mutex_lock(&logm);
        log->info("eventqueue created");
        pthread_mutex_unlock(&logm);
    }
    
    eventqueue::~eventqueue() {
        delete internal;
        delete external;
        if (current!=NULL) delete current;
    }
    
    bool eventqueue::isempty () {
        return (internal->isempty() && external->isempty());
    }
    
    void eventqueue::insertevent (event * e) {
        pthread_mutex_lock(&rwmutex);
        queuestatus();
        if (e->type==event::SIGNAL || e->type==event::CALL) {
            pthread_mutex_lock(&logm);
            log->getStream(log4cpp::Priority::DEBUG) <<" insert external: event("<<e->name <<","<<e->type<<")";
            pthread_mutex_unlock(&logm);
            external->enqueue(e);
        }
        else if (e->type==event::COMPLETION) {
            pthread_mutex_lock(&logm);
            log->getStream(log4cpp::Priority::DEBUG) <<" insert internal: event("<<e->name <<","<<e->type<<")";
            pthread_mutex_unlock(&logm);
            internal->addfirst(e);
        }
        else {
            log->getStream(log4cpp::Priority::DEBUG) << "no insertion for" << *e; 
        }
        empty=false;
        pthread_cond_broadcast(&cond);
        pthread_mutex_unlock(&rwmutex);
        if (e->type==event::CALL) {
            e->waitforacknowledge();
            log->getStream(log4cpp::Priority::DEBUG) << "acknowledged event " << *e; 
        }
        queuestatus();

    }
    
    void eventqueue::queuestatus () {
      pthread_mutex_lock(&rwmutex);
      if (current!=NULL) log->getStream(log4cpp::Priority::DEBUG) << "internal: " << *internal <<", external: " << *external <<", deferred: " << *deferred <<", current= " <<*current<<", completed: " << *completed;
      else log->getStream(log4cpp::Priority::DEBUG) << "internal: " << *internal <<", external: " << *external <<", deferred: " << *deferred <<", completed: " << *completed <<", no current event";
      pthread_mutex_unlock(&rwmutex);
    }
    
    void eventqueue::chosen () {
        log->getStream(log4cpp::Priority::DEBUG) << "chosen" ;
        ischosen=true;
        
    }
    
    void eventqueue::defer () {
        log->getStream(log4cpp::Priority::DEBUG) << "defer event " << *current;
        pthread_mutex_lock(&rwmutex);
        ischosen=false;
        deferred->enqueue(current);
        pthread_mutex_unlock(&rwmutex);
        
    }
    
    void eventqueue::fetch () {
        current=NULL;
        queuestatus();
        log->debug("fetching ...");
      while (internal->isempty() &&
           (!ischosen || deferred->isempty()) &&
           external->isempty()) {
            pthread_mutex_lock(&rwmutex);
           	log->debug("waiting for wakeup call");
           	pthread_cond_wait(&cond,&rwmutex);
           	log->debug("eq woke up");
      }
    
        // Choose an event
      if (!internal->isempty()) {
        current = internal->dequeue();
      }
      else {
        if (ischosen && !deferred->isempty())
          current = deferred->dequeue();
        else {
          current = external->dequeue();
        }
      }
      log->getStream(log4cpp::Priority::DEBUG)<< "fetched event(" << current->name<<","<<current->type <<")";
      pthread_mutex_unlock(&rwmutex);
      queuestatus();
    }
    
    // pre: current != NULL
    event  eventqueue::getcurrent() {
        return *current;
    }
    
    bool eventqueue::match (event e) {
        bool b = e.name==current->name && e.type == current->type;
        //log->getStream(log4cpp::Priority::DEBUG) << "match " << e << " with current  " << *current << " results " <<((b)?"true":"false");
        return b;
    }

    void eventqueue::signal(int i) {
        queuestatus();
        //log->getStream(log4cpp::Priority::DEBUG) << "insert signal event for " << i;
        insertevent(new event(i,event::SIGNAL));
        
    }
    
    void eventqueue::call(int i) {
        log->getStream(log4cpp::Priority::DEBUG) << "insert call event for " << i;
        insertevent(new event(i,event::CALL));
    }
    
    void eventqueue::complete (int i) {
        log->getStream(log4cpp::Priority::DEBUG) << "insert completion event for " << i;
        insertevent(new event(i,event::COMPLETION)); 
        completed->add(new event(i,event::COMPLETION));
    }
    
    void eventqueue::uncomplete (int i) {
        pthread_mutex_lock(&rwmutex);
        internal->remove(event(i,event::COMPLETION));
        pthread_mutex_unlock(&rwmutex);
        log->getStream(log4cpp::Priority::DEBUG) << "remove completion event for " << i;
        completed->removeall(event(i,event::COMPLETION));
    }
    
    void eventqueue::initialisation () {
        log->info("starting state machine");
    }
    
    void eventqueue::acknowledge() {
        log->getStream(log4cpp::Priority::DEBUG) << "acknowledge " ;
        current->acknowledge();
    }
    
    bool eventqueue::contains(event e) {
        //log->getStream(log4cpp::Priority::DEBUG) << "contains " << e << " results " <<((internal->contains(e)||*current==e)?"true":"false");
        queuestatus();
        pthread_mutex_lock(&rwmutex);
        bool b= (internal->contains(e)) || (*current==e);
        pthread_mutex_unlock(&rwmutex);
        return b;
    }
    bool eventqueue::iscompleted(event e) {
    	log->getStream(log4cpp::Priority::DEBUG) << e <<" iscompleted is " <<((completed->contains(e))?"true":"false");
    	return completed->contains(e);
    }
    
    
    
