#include <systemc.h>
#include <iostream.h>
#include "param.h"
/*#include    <log4cpp/Category.hh>
#include    <log4cpp/OstreamAppender.hh>
#include    <log4cpp/BasicLayout.hh>
*/

#ifndef EVENTQUEUE_H_
#define EVENTQUEUE_H_


 class event {
    public:
    enum etype {SIGNAL=1,CALL=2,TIME=3,COMPLETION=4,CHANGE=5,ACK=6,DUMMY=-1};
    etype type;
    int name;
    param parm;
    event() {
    	name=-1;
    	type=DUMMY;
    	parm.sender=-1;
    }
    event (int id,etype type) {
    	this->name=id;
    	this->type=type;
    	parm.sender=-1;
    }
    event (int id,etype type,int sender) {
    	this->name=id;
    	this->type=type;
    	parm.sender=sender;
    }
    
    
    ~event () {}
    inline bool event::operator== (const event&e2) {
      return name==e2.name && type==e2.type;
    }
    friend ostream& operator<< (ostream& s, const event& e) {
      return s << "event(" << e.name << "," <<e.type <<e.parm<<")";
    }
   inline event& operator = (const event& e) {
      name = e.name;
      type = e.type;
      parm= e.parm;
      return *this;
    }
    inline  friend void sc_trace(sc_core::sc_trace_file*& tf,const event& e, const std::string NAME)  {
      sc_trace(tf,e.name, NAME + ".name");
      sc_trace(tf,e.type, NAME + ".type");
    }
    
}; 
 class event_int {
    public:
    event::etype type;
    int name;
    int min,max;
    event_int() {
    	name=-1;
    	type=event::DUMMY;
    }
    event_int (int id,event::etype type) {
    	this->name=id;
    	this->type=type;
    }
    event_int (int id,int min,int max) {
    	this->name=id;
    	this->type=event::TIME;
    	this->min=min;
    	this->max=max;
    }
    
    ~event_int () {}
    inline bool event_int::operator== (const event_int&e2) {
      return name==e2.name && type==e2.type;
    }
    friend ostream& operator<< (ostream& s, const event_int& e) {
      if (e.type==event::TIME) s << "event (" << e.name << "," <<e.type <<",min="<<e.min<<",max="<<e.max<<")";
      else s << "event_int(" << e.name << "," <<e.type <<")";
      return s;
    }

   inline event_int& operator = (const event_int& e) {
      name = e.name;
      type = e.type;
      max = e.max;
      min = e.min;
      return *this;
    }
}; 


template<class T>
class queue_static {
	const static int MAXIMUM_SIZE=5;
	T * elements;
	int size;
	int max_size;
	int  read, write;

	public:	
	queue_static () {
		read=write=size=0;
		max_size=MAXIMUM_SIZE;
		elements=new T[max_size];
		//cout << *this << "\n";
	}
	
	queue_static (int s) {
		read=write=size=0;
	    max_size=s;
		elements=new T[max_size];
		//cout << *this << "\n";
	}

	
	 bool enqueue(T e) {
		if(size <max_size) { 
			//cout << "inserting: " << e<<"\n";
			elements[write]=e;
			write=(write+1)%max_size;
			size++;
			return true;
		} else return false;
	}
	
	 T dequeue() {
		if (size>0) {
			T e=elements[read];
			elements[read]=T();
			read=(read+1)%max_size;
			size--;
			//cout << "dequeuing: " << e<<"\n";
			return e;
		}
		return T();
	}
	
	bool isempty () {
		return read==write;
	}
	
	void print() {
		for (int i=0;i<max_size;i++) cout << elements[i];
	}
	
    friend ostream& operator<< (ostream & s, const queue_static & m) {
		s <<"[";
		for (int i=0;i<m.max_size;i++) {
		    s << m.elements[i]<< ((i+1<m.max_size)?",":"");
		}
		s << "|(read="<<m.read<<",write="<<m.write<<",size="<<m.size<<")]";
		return s;
	}
	bool contains (T t) {
		if (size==0) return false;
		for (int i=0;i<size;i++) {
			int index=(read+i)%max_size;
			if (elements[index]==t) return true;
		}
		return false;
	}
	
	void removeall(T t) {
		if (size==0) return ;
		int found=0;
		int index=read;
		while (elements[index]==t && size>0) {
			elements[index]=T();
     		//cout <<"removeing " << t << " index="<<index<<"\n";
			index=(index+1)%max_size;
			read=(read+1)%max_size;
			size--;
		}
		for (int i=0;i<size;i++) {
		index=(read+i)%max_size;
			if (elements[index]==t) {
				elements[index]=T();
				found++;
        		//cout <<"removeing " << t << " index="<<index<<"\n";
				//cout << "nulling " << index<<"\n";
			}
			else if (found>0) {
				elements[(index-found+max_size)%max_size]=elements[index];
				elements[index]=T();
			}
		}
		write=(write-found+max_size)%max_size;
		size=size-found;
		cout << *this <<"\n";
		
	}
};

SC_MODULE(eq_module) {
	const static int MAX_SIZE=5;
	sc_inout<bool> inbusy;
	sc_in<event> input;
	sc_out<event> output;
	sc_inout<bool> ack;
	sc_inout<bool> blocked;
	sc_in<bool> clk;
	queue_static<event> * ev_queue;
	
	SC_CTOR(eq_module) {
		ev_queue=new queue_static<event>(MAX_SIZE);
		SC_METHOD(receive_event);
		sensitive_pos << inbusy;
		SC_METHOD(fetch);
		sensitive_pos << ack;
	}
	
	void receive_event() {
		event e=input.read();
		//cout << "da very first receiving of "<< e << "\n";
		if (e.type==event::ACK) {
			blocked=false;
			inbusy=false;
			next_trigger();
			//cout << " eq received ACK\n";
			return;
		}
		if (!(e==event())) {
          ev_queue->enqueue(input.read());
	  	  inbusy=false;
	  	  if (ack==true) {
	  	  	event e = ev_queue->dequeue();
	  		output.write(e);
	  		ack.write(false);
			//cout << "eq receive writing " << e << "\n";
	  	  }
	  	  //cout << "eq received "<<*ev_queue<<"\n";
	  	  next_trigger();
		}
		inbusy=false;
	}
	
	void fetch() {
		if (ack==true && (!ev_queue->isempty())) {
			event e= ev_queue->dequeue();
			if (!(e==event())) {
			  output.write(e);
			  ack.write(false);
			  next_trigger();
   			  //cout << "eq : fetch writing" << e << "\n";
			}
		}
	}
	
	
	bool isempty () {
		return ev_queue->isempty();
	}
	
	
    friend ostream& operator<< (ostream & s, const eq_module & m) {
		s << m.ev_queue;
		return s;
	}
};

#endif /*EVENTQUEUE_H_*/
