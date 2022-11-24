#include <iostream.h>
#include    <log4cpp/Category.hh>
#include    <log4cpp/OStreamAppender.hh>
#include    <log4cpp/BasicLayout.hh>
#include <string.h>


#ifndef EVENTQUEUE_H_
#define EVENTQUEUE_H_

template <class T,class V>
class map {
    int size;
    class elem {
      public:
        T * key;
        V * data;
        bool hasnext;
        elem * next;

        elem (T * t,V * v) {
          hasnext=false;
          data = v;
          key = t;
          next=NULL;
        }
        
        ~elem () {
        	delete data;
        	delete key;
        }
        
        friend ostream& operator<< (ostream& s, const elem& e) {
        	s << "("<< *e.key << "->" <<*e.data << ") ";
        	return s;
        }
    };

    elem * first;
 	public:
     map () {
        	size=0;
     }      

     friend ostream& operator<< (ostream& s, const map<T,V>& q) {
        if (q.size==0) return s;
        elem * e = q.first;
        s << *e <<" ";
        while (e->hasnext) {
          s << *e->next <<" ";
          e = e->next;
        }
        return s;
    }


     void add (T * key,V*data) {
        if (size==0) {
            first=new elem (key,data);
        }
        else {
        elem *tmp = first;
        
        first= new elem(key,data);
        first->hasnext=true;
        first->next=tmp;
        }
        size++;
    }

    void remove (T t) {
      if (size==0) return;
      while (*first->key==t) {
        elem * tmp=first->next;
        delete first;
        first=tmp;
        size--;
        if (size==0) return;
      }
      elem * tmp =first;
      if (size==0) return;
      while (size>0 && tmp->hasnext) {
        if (*tmp->next->key==t) {
            if (tmp->next->hasnext) {
              elem * tmp2;
              tmp2=tmp->next->next;
              //cout << "removeing " << *tmp->next->data << "\n";
              delete tmp->next;
              tmp->next=tmp2;
              tmp=tmp->next;
            } 
            else { 
            delete tmp->next;
            tmp->hasnext=false;
            }
            size--;
            if (size==0) return;
        } else 
          tmp=tmp->next;
      }
      
     }
     
     bool contains(T e) {
     elem * tmp = first;
      if (size==0) return false;
      if (e==*tmp->key) return true;
       while(tmp->hasnext) {
         if (e==*tmp->next->key) return true;
         tmp=tmp->next;
       }
       return false;
    }
	
	
};

template <class T>
class set {
    int size;
    class elem {
      public:
        T * data;
        bool hasnext;
        elem * next;

        elem (T * t) {
          hasnext=false;
          data = t;
        }
        
        ~elem () {
        	delete data;
        }
        
        void setnext (elem e) {
            next=&e;
        }
        friend ostream& operator<< (ostream& s, const elem& e) {
          s << "("<< *e.data << ") ";
          return s;
        }
    };
    elem * first;

    public:
    set() {
        size=0;
    }
  
    ~set() { 
        while (size>0) {
          elem * e= first;
          first = first->next;
          delete e;
          size--;
        }
    }
     friend ostream& operator<< (ostream& s, const set<T>& q) {
        if (q.size==0) return s;
        elem * e = q.first;
        s << *e->data << " ";
        while (e->hasnext) {
          s << *e->next->data << " ";
          e = e->next;
        }
        return s;
    }
    void setstatus () {
        elem * e = first;
        for (int i=0;i<size;i++) {
          //cout <<  *e->data << ((e->hasnext)?"(hasnext)":"" ) << " ";
          e = e->next;
        }
    }
    bool isempty () {
        return (size==0);
    }

    void add (T * data) {
        elem * e =new elem (data);
        if (size==0) {
            first=e;
        }
        else {
        e->hasnext=true;
        e->next=first;
        first=e;
        }
        size++;
    }

    void removeall (T t) {
      if (size==0) return;
      while (*first->data==t) {
        elem * tmp=first->next;
        delete first;
        first=tmp;
        size--;
        if (size==0) return;
      }
      elem * tmp =first;
      if (size==0) return;
      while (size>0 && tmp->hasnext) {
        if (*tmp->next->data==t) {
            if (tmp->next->hasnext) {
              elem * tmp2;
              tmp2=tmp->next->next;
              //cout << "removeing " << *tmp->next->data << "\n";
              delete tmp->next;
              tmp->next=tmp2;
              tmp=tmp->next;
            } 
            else { 
            delete tmp->next;
            tmp->hasnext=false;
            }
            size--;
            if (size==0) return;
        } else 
          tmp=tmp->next;
      }
      
     }
     
     bool contains(T e) {
     elem * tmp = first;
      if (size==0) return false;
      if (e==*tmp->data) return true;
       while(tmp->hasnext) {
         if (e==*tmp->next->data) return true;
         tmp=tmp->next;
       }
       return false;
    }
     

};

template <class T>
class queue {

    class elem {
      public:
        T * data;
        bool hasnext;
        elem * next;

        elem (T * t) {
          hasnext=false;
          data = t;
        }
        
        ~elem () {
        }
        
        void setnext (elem e) {
            next=&e;
        }
        friend ostream& operator<< (ostream& s, const elem& e) {
          s << "("<< *e.data << ") ";
          return s;
        }
        
    };
    elem * last;
  protected:
    int size;
    elem * first;
    
  public: 
    queue() {
        size=0;
    }
  
    ~queue() { 
        while (size>0) {
          elem * e= first;
          first = first->next;
          delete e;
          size--;
        }
    }

    int elements() {return size;}
    
    void enqueue (T * data) {
         if (size==0) {
            first=new elem (data);
        }
        else {
        elem *tmp = first;
        
        first= new elem(data);
        first->hasnext=true;
        first->next=tmp;
        }
        size++;
    }
    
     friend ostream& operator<< (ostream& s, const queue<T>& q) {
        if (q.size==0) return s;
        elem * e = q.first;
        s << *e->data << " ";
        while (e->hasnext) {
          s << *e->next->data << " ";
          e = e->next;
        }
        return s;
    }
    
    T * dequeue () {
        T * data = first->data;
        elem * tmp = first;
        //cout << "DEQUEUE" << *first->data << "\n";
        first = first->next;
        delete tmp;
        size--;
        return data;
    }
    
    bool isempty () {
        return (size==0);
    }
    
    void addfirst (T * data) {
        elem * e = new elem (data);
        if (size>0) {
            e->hasnext=true;
            e->next=first;
        }
        first=e;
        size++;
    }
    
    void remove (T t) {
      if (size==0) return;
      while (*first->data==t) {
        elem * tmp=first->next;
        //cout << "removing " << *first->data << "\n";
        //delete first;
        first=tmp;
        size--;
        if (size==0) return;
      }
      elem * tmp =first;
      if (size==0) return;
      while (size>0 && tmp->hasnext) {
        if (*tmp->next->data==t) {
            if (tmp->next->hasnext) {
              elem * tmp2;
              tmp2=tmp->next->next;
              //cout << "removeing " << *tmp->next->data << "\n";
              delete tmp->next;
              tmp->next=tmp2;
              tmp=tmp->next;
            } 
            else { 
            //delete tmp->next;
            tmp->hasnext=false;
            }
            size--;
            if (size==0) return;
        } else 
          tmp=tmp->next;
      }
      
     }
 
     bool contains(T e) {
     elem * tmp = first;
      if (size==0) return false;
      if (e==*tmp->data) return true;
       while(tmp->hasnext) {
         if (e==*tmp->next->data) return true;
         tmp=tmp->next;
       }
       return false;
    }
 
};

class event {
    bool acknowledged;
    pthread_mutex_t mutex;
    pthread_cond_t cond;
    public:
    enum etype {SIGNAL=1,CALL=2,COMPLETION=3,TIME=4};
    etype type;
    int name;
    event (int id,etype type);
    ~event () {}
    void acknowledge();
    void waitforacknowledge();
    bool event::operator== (const event&e2) {
      return name==e2.name && type==e2.type;
    }
    friend ostream& operator<< (ostream& s, const event& e) {
      return s << "event (" << e.name << "," <<e.type <<")";
    }
};

class eventqueue{
    queue<event> * internal;
    queue<event> * external;
    queue<event> * deferred;
    set<event> * completed;
    event * current;
    bool ischosen, empty;
    bool running;
    char * name;
    pthread_mutex_t rwmutex;
    pthread_mutex_t logm;
    pthread_cond_t cond;
  public:
  static  log4cpp::OstreamAppender app;
  static int logpriority;
  log4cpp::Category * log;
  eventqueue (char * name);
  ~eventqueue();
  void fetch ();
  bool match(event e);
  void defer ();
  void chosen ();
  void acknowledge ();
  bool isempty ();
  void insertevent (event *e);
  void complete(int i);
  void signal (int i);
  void call (int i);
  void uncomplete(int i);
  void initialisation();
  event getcurrent();
  void  queuestatus();
  bool contains(event e);
  bool iscompleted(event e);
  static void configdebug(int priority);
};

#endif /*EVENTQUEUE_H_*/
