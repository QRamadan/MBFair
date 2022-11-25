#define Initial0 1
#define Initial02Off 1
#define empty 0
#define completion_queue_size 2
#define max_parameters 1
#define On 2
#define send_off 1
#define RECEIVE 1
#define Off 3
#define send_on 2
#define success 0
#define TERMINATION 2
#define Initial0_G0 1
#define Initial02Off_G0 1
#define Off_G0 2
#define call_setLevel 2
#define send_on_G0 1
#define On_G0 3
#define cmplOn 1
#define completion_top_On 3
#define number_objects 8
#define queue_size 5
#define send_ground 3
#define SEND 0
#define Initial0_G1 1
#define Initial02Closed 1
#define Closed 2
#define send_open 3
#define Open 3
#define call_check 1
#define send_close 2
#define Initial0_G2 1
#define Initial02Idle 1
#define Idle 2
#define send_brew 1
#define Idle2Brewing 2
#define Brewing 3
#define Grinding 4
#define Grinding2FinalState0 3
#define SetGrindingLevel 5
#define completion_top_Brewing_Region0region1_SetGrindingL 7
#define cmplSetGrindingLevel 3
#define Data_gl 2
#define TurnOnGrinder 6
#define cmplTurnOnGrinder 3
#define completion_top_Brewing_Region0region1_TurnOnGrinde 8
#define Heating 7
#define send_heated 4
#define Heating2FinalState0 4
#define TurnOnHeater 8
#define completion_top_Brewing_Region1region2_TurnOnHeater 9
#define cmplTurnOnHeater 5
#define send_on_G1 1
#define completion_top_Brewing 10
#define cmplBrewing 1
#define Brewing2Dispensing 5
#define send_notEnoughBeans 5
#define Brewing2Idle 6
#define Dispensing 9
#define CheckWater 10
#define completion_top_Dispensing_CheckWater 11
#define cmplCheckWater 2
#define Data_wl 3
#define Checking 11
#define Pouring 12
#define time_top_Dispensing_Pouring_after__data_wl__data_w 12
#define tmrTime_top_Dispensing_Pouring_after__data_wl__dat 0
#define Pouring2FinalState0 7
#define send_enoughWater 2
#define completion_top_Dispensing 13
#define cmplDispensing 1
#define Dispensing2Idle 8
#define send_notEnoughWater 6
#define Dispensing2Idle_G0 9
#define FinalState0 13
#define cmplRegion0region1 2
#define FinalState0_G0 14
#define cmplRegion1region2 4
#define Initial0_G3 15
#define Initial02CheckWater 10
#define Initial02Pouring 11
#define FinalState0_G1 16
#define send_enjoy 1
#define send_refillWater 4
#define send_refillBeans 3
#define Initial0_G4 17
#define Initial02TurnOnGrinder 12
#define Initial02SetGrindingLevel 13
#define Initial0_G5 18
#define Initial02TurnOnHeater 14
#define Initial0_G6 1
#define Initial02Off_G1 1
#define Off_G1 2
#define On_G1 3
#define cmplOn_G0 1
#define completion_top_On_G0 2
#define Initial0_G7 1
#define Initial02Menu 1
#define Working 2
#define Working2Ready 2
#define Working2Ready_G0 3
#define Working2Ready_G1 4
#define Menu 3
#define Ready 4
#define send_large 2
#define Ready2Working 5
#define send_small 5
#define Ready2Working_G0 6
#define Data_LARGE 5
#define Data_SMALL 3
#define Initial0_G8 5
#define Initial02Ready 7
#define obj_ui 1
#define obj_ctrl 2
#define obj_led0 3
#define obj_led1 4
#define obj_led2 5
#define obj_grinder 6
#define obj_heater 7
#define obj_waterTank 8

chan observer = [0] of {byte, int, byte, int, byte};
chan event_queues[number_objects] = [queue_size] of {byte, byte, int, chan};

pid processIds[number_objects];
int Data_wl_G0 = 3;

proctype Control(chan event_queue; byte this; byte initialiser_grinder0; byte initialiser_heater0; byte initialiser_waterTank0; byte initialiser_data0; byte initialiser_ui0) {
  chan ack_in = [0] of {bit};
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  byte state1_transition;
  bool timedOut[1];
  byte grinder;
  byte data;
  byte heater;
  byte current_event;
  bool completed[6];
  byte state2;
  byte state1;
  byte state4;
  byte state0_transition;
  byte state0;
  byte ui;
  byte state2_transition;
  byte sender;
  byte state4_transition;
  byte waterTank;
  int parameters[max_parameters];

  xr event_queue;
  atomic {
    grinder = initialiser_grinder0;
    heater = initialiser_heater0;
    waterTank = initialiser_waterTank0;
    data = initialiser_data0;
    ui = initialiser_ui0;
    state0 = Initial0_G2;
    state0_transition = Initial02Idle;
    goto transitionFiring
  };
  main:
  current_event = empty;
  sender = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,sender,parameters[0],ack_out
  fi;
  atomic {
    if
    :: state0 == Idle && current_event == send_brew ->
       state0_transition = Idle2Brewing;
       goto top_label
    :: else
    fi;
    if
    :: state0 == Brewing ->
       if
       :: state2 == Grinding && current_event == send_ground ->
          state2_transition = Grinding2FinalState0;
          goto top_label
       :: state2 == SetGrindingLevel && current_event == completion_top_Brewing_Region0region1_SetGrindingL && completed[cmplSetGrindingLevel] == true ->
          completed[cmplSetGrindingLevel] = false;
          state2 = empty;
          observer!SEND,this,grinder,call_setLevel,Data_gl;
          event_queues[grinder-1]!call_setLevel,this,Data_gl,ack_in;
          ack_in?_;
          state2 = TurnOnGrinder;
          completed[cmplTurnOnGrinder] = true;
          internal_queue!completion_top_Brewing_Region0region1_TurnOnGrinde;
          goto main
       :: state2 == TurnOnGrinder && current_event == completion_top_Brewing_Region0region1_TurnOnGrinde && completed[cmplTurnOnGrinder] == true ->
          completed[cmplTurnOnGrinder] = false;
          state2 = empty;
          observer!SEND,this,grinder,send_on_G0,empty;
          event_queues[grinder-1]!send_on_G0,this,empty,ack_in;
          state2 = Grinding;
          goto main
       :: else
       fi;
       top_Brewing_Region0region1_label:
       skip;
       if
       :: state4 == Heating && current_event == send_heated ->
          state4_transition = Heating2FinalState0;
          goto top_label
       :: state4 == TurnOnHeater && current_event == completion_top_Brewing_Region1region2_TurnOnHeater && completed[cmplTurnOnHeater] == true ->
          completed[cmplTurnOnHeater] = false;
          state4 = empty;
          observer!SEND,this,heater,send_on_G1,empty;
          event_queues[heater-1]!send_on_G1,this,empty,ack_in;
          state4 = Heating;
          goto main
       :: else
       fi;
       top_Brewing_Region1region2_label:
       skip
    :: else
    fi;
    if
    :: state0 == Brewing && current_event == completion_top_Brewing && completed[cmplBrewing] == true && state2_transition == empty && state4_transition == empty ->
       state0_transition = Brewing2Dispensing;
       goto top_label
    :: state0 == Brewing && current_event == send_notEnoughBeans && state2_transition == empty && state4_transition == empty ->
       state0_transition = Brewing2Idle;
       goto top_label
    :: else
    fi;
    top_Brewing_label:
    skip;
    if
    :: state0 == Dispensing ->
       if
       :: state1 == CheckWater && current_event == completion_top_Dispensing_CheckWater && completed[cmplCheckWater] == true ->
          completed[cmplCheckWater] = false;
          state1 = empty;
          observer!SEND,this,waterTank,call_check,Data_wl;
          event_queues[waterTank-1]!call_check,this,Data_wl,ack_in;
          ack_in?_;
          state1 = Checking;
          goto main
       :: state1 == Pouring && current_event == time_top_Dispensing_Pouring_after__data_wl__data_w && timedOut[tmrTime_top_Dispensing_Pouring_after__data_wl__dat] == true ->
          state1_transition = Pouring2FinalState0;
          goto top_label
       :: state1 == Checking && current_event == send_enoughWater ->
          if
          :: current_event <= 6 ->
             observer!RECEIVE,sender,this,current_event,parameters[0]
          :: else
          fi;
          state1 = empty;
          observer!SEND,this,waterTank,send_open,empty;
          event_queues[waterTank-1]!send_open,this,empty,ack_in;
          state1 = Pouring;
          event_queue!time_top_Dispensing_Pouring_after__data_wl__data_w,this,empty,ack_in;
          timedOut[tmrTime_top_Dispensing_Pouring_after__data_wl__dat] = true;
          goto main
       :: else
       fi
    :: else
    fi;
    if
    :: state0 == Dispensing && current_event == completion_top_Dispensing && completed[cmplDispensing] == true ->
       state0_transition = Dispensing2Idle;
       goto top_label
    :: state0 == Dispensing && current_event == send_notEnoughWater ->
       state0_transition = Dispensing2Idle_G0;
       goto top_label
    :: else
    fi;
    top_Dispensing_label:
    skip;
    top_label:
    skip;
    if
    :: current_event <= 6 ->
       observer!RECEIVE,sender,this,current_event,parameters[0]
    :: else
    fi;
    transitionFiring:
    if
    :: state0_transition != empty ->
       if
       :: state0_transition == Brewing2Dispensing ->
          state0_transition = empty;
          if
          :: state2 == Grinding ->
             state2 = empty
          :: state2 == FinalState0 ->
             state2 = empty
          :: state2 == SetGrindingLevel ->
             completed[cmplSetGrindingLevel] = false;
             state2 = empty
          :: state2 == TurnOnGrinder ->
             completed[cmplTurnOnGrinder] = false;
             state2 = empty
          fi;
          completed[cmplRegion0region1] = false;
          if
          :: state4 == Heating ->
             state4 = empty
          :: state4 == FinalState0_G0 ->
             state4 = empty
          :: state4 == TurnOnHeater ->
             completed[cmplTurnOnHeater] = false;
             state4 = empty
          fi;
          completed[cmplRegion1region2] = false;
          completed[cmplBrewing] = false;
          state0 = Dispensing;
          state1 = Initial0_G3;
          if
          :: true ->
             state1_transition = Initial02CheckWater
          :: true ->
             state1_transition = Initial02Pouring
          :: else ->
             assert(false)
          fi
       :: state0_transition == Dispensing2Idle ->
          state0_transition = empty;
          if
          :: state1 == CheckWater ->
             completed[cmplCheckWater] = false;
             state1 = empty
          :: state1 == Pouring ->
             if
             :: event_queue??[current_event,sender,parameters[0],ack_out] ->
                event_queue??current_event,sender,parameters[0],ack_out
             :: else
             fi;
             timedOut[tmrTime_top_Dispensing_Pouring_after__data_wl__dat] = false;
             state1 = empty
          :: state1 == FinalState0_G1 ->
             state1 = empty
          :: state1 == Checking ->
             state1 = empty
          fi;
          completed[cmplDispensing] = false;
          state0 = empty;
          observer!SEND,this,ui,send_enjoy,empty;
          event_queues[ui-1]!send_enjoy,this,empty,ack_in;
          state0 = Idle
       :: state0_transition == Dispensing2Idle_G0 ->
          state0_transition = empty;
          if
          :: state1 == CheckWater ->
             completed[cmplCheckWater] = false;
             state1 = empty
          :: state1 == Pouring ->
             if
             :: event_queue??[current_event,sender,parameters[0],ack_out] ->
                event_queue??current_event,sender,parameters[0],ack_out
             :: else
             fi;
             timedOut[tmrTime_top_Dispensing_Pouring_after__data_wl__dat] = false;
             state1 = empty
          :: state1 == FinalState0_G1 ->
             state1 = empty
          :: state1 == Checking ->
             state1 = empty
          fi;
          completed[cmplDispensing] = false;
          state0 = empty;
          observer!SEND,this,ui,send_refillWater,empty;
          event_queues[ui-1]!send_refillWater,this,empty,ack_in;
          state0 = Idle
       :: state0_transition == Initial02Idle ->
          state0_transition = empty;
          state0 = empty;
          state0 = Idle
       :: state0_transition == Brewing2Idle ->
          state0_transition = empty;
          if
          :: state2 == Grinding ->
             state2 = empty
          :: state2 == FinalState0 ->
             state2 = empty
          :: state2 == SetGrindingLevel ->
             completed[cmplSetGrindingLevel] = false;
             state2 = empty
          :: state2 == TurnOnGrinder ->
             completed[cmplTurnOnGrinder] = false;
             state2 = empty
          fi;
          completed[cmplRegion0region1] = false;
          if
          :: state4 == Heating ->
             state4 = empty
          :: state4 == FinalState0_G0 ->
             state4 = empty
          :: state4 == TurnOnHeater ->
             completed[cmplTurnOnHeater] = false;
             state4 = empty
          fi;
          completed[cmplRegion1region2] = false;
          completed[cmplBrewing] = false;
          observer!SEND,this,ui,send_refillBeans,empty;
          event_queues[ui-1]!send_refillBeans,this,empty,ack_in;
          state0 = Idle
       :: state0_transition == Idle2Brewing ->
          state0_transition = empty;
          state0 = empty;
          state0 = Brewing;
          state2 = Initial0_G4;
          if
          :: true ->
             state2_transition = Initial02TurnOnGrinder
          :: true ->
             state2_transition = Initial02SetGrindingLevel
          :: else ->
             assert(false)
          fi;
          state4 = Initial0_G5;
          state4_transition = Initial02TurnOnHeater
       :: else
       fi
    :: else ->
       if
       :: state1_transition != empty ->
          if
          :: state1_transition == Pouring2FinalState0 ->
             state1_transition = empty;
             if
             :: event_queue??[current_event,sender,parameters[0],ack_out] ->
                event_queue??current_event,sender,parameters[0],ack_out
             :: else
             fi;
             timedOut[tmrTime_top_Dispensing_Pouring_after__data_wl__dat] = false;
             state1 = empty;
             observer!SEND,this,waterTank,send_close,empty;
             event_queues[waterTank-1]!send_close,this,empty,ack_in;
             state1 = FinalState0_G1;
             completed[cmplDispensing] = true;
             internal_queue!completion_top_Dispensing
          :: state1_transition == Initial02CheckWater ->
             state1_transition = empty;
             state1 = empty;
             state1 = CheckWater;
             completed[cmplCheckWater] = true;
             internal_queue!completion_top_Dispensing_CheckWater
          :: state1_transition == Initial02Pouring ->
             state1_transition = empty;
             state1 = empty;
             state1 = Pouring;
             event_queue!time_top_Dispensing_Pouring_after__data_wl__data_w,this,empty,ack_in;
             timedOut[tmrTime_top_Dispensing_Pouring_after__data_wl__dat] = true
          :: else
          fi
       :: else ->
          if
          :: state2_transition != empty ->
             if
             :: state2_transition == Initial02TurnOnGrinder ->
                state2_transition = empty;
                state2 = empty;
                state2 = TurnOnGrinder;
                completed[cmplTurnOnGrinder] = true;
                internal_queue!completion_top_Brewing_Region0region1_TurnOnGrinde
             :: state2_transition == Initial02SetGrindingLevel ->
                state2_transition = empty;
                state2 = empty;
                state2 = SetGrindingLevel;
                completed[cmplSetGrindingLevel] = true;
                internal_queue!completion_top_Brewing_Region0region1_SetGrindingL
             :: state2_transition == Grinding2FinalState0 ->
                state2_transition = empty;
                state2 = empty;
                state2 = FinalState0;
                if
                :: state4 == FinalState0_G0 ->
                   completed[cmplBrewing] = true;
                   internal_queue!completion_top_Brewing
                :: else
                fi
             :: else
             fi
          :: else ->
             if
             :: state4_transition != empty ->
                if
                :: state4_transition == Initial02TurnOnHeater ->
                   state4_transition = empty;
                   state4 = empty;
                   state4 = TurnOnHeater;
                   completed[cmplTurnOnHeater] = true;
                   internal_queue!completion_top_Brewing_Region1region2_TurnOnHeater
                :: state4_transition == Heating2FinalState0 ->
                   state4_transition = empty;
                   state4 = empty;
                   state4 = FinalState0_G0;
                   if
                   :: state2 == FinalState0 ->
                      completed[cmplBrewing] = true;
                      internal_queue!completion_top_Brewing
                   :: else
                   fi
                :: else
                fi
             :: else
             fi
          fi
       fi
    fi;
    if
    :: state1_transition != empty || state0_transition != empty || state2_transition != empty || state4_transition != empty ->
       goto transitionFiring
    :: else
    fi;
    goto main
  };
  end_machine0:
  observer!TERMINATION,this,empty,empty,0;
  end_machine:
  success
}

proctype Heater(chan event_queue; byte this; byte initialiser_control0) {
  chan ack_in = [0] of {bit};
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  bit state0_transition;
  byte state0;
  byte sender;
  byte control;
  byte current_event;
  bool completed[2];
  int parameters[max_parameters];

  xr event_queue;
  atomic {
    control = initialiser_control0;
    state0 = Initial0_G6;
    state0_transition = Initial02Off_G1;
    goto transitionFiring
  };
  main:
  current_event = empty;
  sender = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,sender,parameters[0],ack_out
  fi;
  atomic {
    if
    :: state0 == Off_G1 && current_event == send_on_G1 ->
       if
       :: current_event <= 1 ->
          observer!RECEIVE,sender,this,current_event,parameters[0]
       :: else
       fi;
       state0 = empty;
       state0 = On_G1;
       completed[cmplOn_G0] = true;
       internal_queue!completion_top_On_G0;
       goto main
    :: state0 == On_G1 && current_event == completion_top_On_G0 && completed[cmplOn_G0] == true ->
       completed[cmplOn_G0] = false;
       state0 = empty;
       observer!SEND,this,control,send_heated,empty;
       event_queues[control-1]!send_heated,this,empty,ack_in;
       state0 = Off_G1;
       goto main
    :: else
    fi;
    top_label:
    skip;
    if
    :: current_event <= 1 ->
       observer!RECEIVE,sender,this,current_event,parameters[0]
    :: else
    fi;
    transitionFiring:
    if
    :: state0_transition == Initial02Off_G1 ->
       state0_transition = empty;
       state0 = empty;
       state0 = Off_G1
    :: else
    fi;
    goto main
  };
  end_machine0:
  observer!TERMINATION,this,empty,empty,0;
  end_machine:
  success
}

proctype LED(chan event_queue; byte this) {
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  bit state0_transition;
  byte state0;
  byte sender;
  byte current_event;
  int parameters[max_parameters];

  xr event_queue;
  atomic {
    state0 = Initial0;
    state0_transition = Initial02Off;
    goto transitionFiring
  };
  main:
  current_event = empty;
  sender = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,sender,parameters[0],ack_out
  fi;
  atomic {
    if
    :: state0 == On && current_event == send_off ->
       if
       :: current_event <= 2 ->
          observer!RECEIVE,sender,this,current_event,parameters[0]
       :: else
       fi;
       state0 = empty;
       state0 = Off;
       goto main
    :: state0 == Off && current_event == send_on ->
       if
       :: current_event <= 2 ->
          observer!RECEIVE,sender,this,current_event,parameters[0]
       :: else
       fi;
       state0 = empty;
       state0 = On;
       goto main
    :: else
    fi;
    top_label:
    skip;
    if
    :: current_event <= 2 ->
       observer!RECEIVE,sender,this,current_event,parameters[0]
    :: else
    fi;
    transitionFiring:
    if
    :: state0_transition == Initial02Off ->
       state0_transition = empty;
       state0 = empty;
       state0 = Off
    :: else
    fi;
    goto main
  };
  end_machine0:
  observer!TERMINATION,this,empty,empty,0;
  end_machine:
  success
}

proctype UserInterface(chan event_queue; byte this; byte initialiser_data0; byte initialiser_leds0; byte initialiser_leds1; byte initialiser_leds2; byte initialiser_control0) {
  chan ack_in = [0] of {bit};
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  byte state0_transition;
  byte state1_transition;
  byte state0;
  byte data;
  byte sender;
  byte leds[3];
  byte control;
  byte current_event;
  int parameters[max_parameters];
  byte state1;

  xr event_queue;
  atomic {
    data = initialiser_data0;
    leds[0] = initialiser_leds0;
    leds[1] = initialiser_leds1;
    leds[2] = initialiser_leds2;
    control = initialiser_control0;
    state0 = Initial0_G7;
    state0_transition = Initial02Menu;
    goto transitionFiring
  };
  main:
  current_event = empty;
  sender = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,sender,parameters[0],ack_out
  fi;
  atomic {
    if
    :: state0 == Working && current_event == send_refillWater ->
       state0_transition = Working2Ready;
       goto top_label
    :: state0 == Working && current_event == send_enjoy ->
       state0_transition = Working2Ready_G0;
       goto top_label
    :: state0 == Working && current_event == send_refillBeans ->
       state0_transition = Working2Ready_G1;
       goto top_label
    :: else
    fi;
    if
    :: state0 == Menu ->
       if
       :: state1 == Ready && current_event == send_large ->
          state0_transition = Ready2Working;
          goto top_label
       :: state1 == Ready && current_event == send_small ->
          state0_transition = Ready2Working_G0;
          goto top_label
       :: else
       fi
    :: else
    fi;
    top_Menu_label:
    skip;
    top_label:
    skip;
    if
    :: current_event <= 5 ->
       observer!RECEIVE,sender,this,current_event,parameters[0]
    :: else
    fi;
    transitionFiring:
    if
    :: state0_transition != empty ->
       if
       :: state0_transition == Ready2Working ->
          state0_transition = empty;
          state1 = empty;
          state0 = empty;
          observer!SEND,this,leds[1],send_off,empty;
          event_queues[leds[1]-1]!send_off,this,empty,ack_in;
          observer!SEND,this,leds[2],send_off,empty;
          event_queues[leds[2]-1]!send_off,this,empty,ack_in;
          observer!SEND,this,leds[0],send_on,empty;
          event_queues[leds[0]-1]!send_on,this,empty,ack_in;
          Data_wl_G0 = Data_LARGE;
          observer!SEND,this,control,send_brew,empty;
          event_queues[control-1]!send_brew,this,empty,ack_in;
          state0 = Working
       :: state0_transition == Ready2Working_G0 ->
          state0_transition = empty;
          state1 = empty;
          state0 = empty;
          observer!SEND,this,leds[1],send_off,empty;
          event_queues[leds[1]-1]!send_off,this,empty,ack_in;
          observer!SEND,this,leds[2],send_off,empty;
          event_queues[leds[2]-1]!send_off,this,empty,ack_in;
          observer!SEND,this,leds[0],send_on,empty;
          event_queues[leds[0]-1]!send_on,this,empty,ack_in;
          Data_wl_G0 = Data_SMALL;
          observer!SEND,this,control,send_brew,empty;
          event_queues[control-1]!send_brew,this,empty,ack_in;
          state0 = Working
       :: state0_transition == Working2Ready ->
          state0_transition = empty;
          state0 = empty;
          observer!SEND,this,leds[0],send_off,empty;
          event_queues[leds[0]-1]!send_off,this,empty,ack_in;
          observer!SEND,this,leds[2],send_on,empty;
          event_queues[leds[2]-1]!send_on,this,empty,ack_in;
          state0 = Menu;
          state1 = Ready
       :: state0_transition == Working2Ready_G0 ->
          state0_transition = empty;
          state0 = empty;
          observer!SEND,this,leds[0],send_off,empty;
          event_queues[leds[0]-1]!send_off,this,empty,ack_in;
          state0 = Menu;
          state1 = Ready
       :: state0_transition == Working2Ready_G1 ->
          state0_transition = empty;
          state0 = empty;
          observer!SEND,this,leds[0],send_off,empty;
          event_queues[leds[0]-1]!send_off,this,empty,ack_in;
          observer!SEND,this,leds[1],send_on,empty;
          event_queues[leds[1]-1]!send_on,this,empty,ack_in;
          state0 = Menu;
          state1 = Ready
       :: state0_transition == Initial02Menu ->
          state0_transition = empty;
          state0 = empty;
          state0 = Menu;
          state1 = Initial0_G8;
          state1_transition = Initial02Ready
       :: else
       fi
    :: else ->
       if
       :: state1_transition != empty ->
          if
          :: state1_transition == Initial02Ready ->
             state1_transition = empty;
             state1 = empty;
             state1 = Ready
          :: else
          fi
       :: else
       fi
    fi;
    if
    :: state0_transition != empty || state1_transition != empty ->
       goto transitionFiring
    :: else
    fi;
    goto main
  };
  end_machine0:
  observer!TERMINATION,this,empty,empty,0;
  end_machine:
  success
}

proctype Grinder(chan event_queue; byte this; byte initialiser_control0) {
  chan ack_in = [0] of {bit};
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  bit state0_transition;
  byte state0;
  byte sender;
  byte control;
  byte current_event;
  bool completed[2];
  int parameters[max_parameters];

  xr event_queue;
  atomic {
    control = initialiser_control0;
    state0 = Initial0_G0;
    state0_transition = Initial02Off_G0;
    goto transitionFiring
  };
  main:
  current_event = empty;
  sender = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,sender,parameters[0],ack_out
  fi;
  atomic {
    if
    :: state0 == Off_G0 && current_event == call_setLevel ->
       if
       :: current_event <= 2 ->
          observer!RECEIVE,sender,this,current_event,parameters[0]
       :: else
       fi;
       ack_out!1;
       state0 = empty;
       state0 = Off_G0;
       goto main
    :: state0 == Off_G0 && current_event == send_on_G0 ->
       if
       :: current_event <= 2 ->
          observer!RECEIVE,sender,this,current_event,parameters[0]
       :: else
       fi;
       state0 = empty;
       state0 = On_G0;
       completed[cmplOn] = true;
       internal_queue!completion_top_On;
       goto main
    :: state0 == On_G0 && current_event == completion_top_On && completed[cmplOn] == true ->
       completed[cmplOn] = false;
       state0 = empty;
       observer!SEND,this,control,send_ground,empty;
       event_queues[control-1]!send_ground,this,empty,ack_in;
       state0 = Off_G0;
       goto main
    :: else
    fi;
    top_label:
    skip;
    if
    :: current_event == call_setLevel ->
       ack_out!1
    :: else
    fi;
    if
    :: current_event <= 2 ->
       observer!RECEIVE,sender,this,current_event,parameters[0]
    :: else
    fi;
    transitionFiring:
    if
    :: state0_transition == Initial02Off_G0 ->
       state0_transition = empty;
       state0 = empty;
       state0 = Off_G0
    :: else
    fi;
    goto main
  };
  end_machine0:
  observer!TERMINATION,this,empty,empty,0;
  end_machine:
  success
}

proctype WaterTank(chan event_queue; byte this; byte initialiser_control0) {
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  bit state0_transition;
  byte state0;
  byte sender;
  byte control;
  byte current_event;
  int parameters[max_parameters];

  xr event_queue;
  atomic {
    control = initialiser_control0;
    state0 = Initial0_G1;
    state0_transition = Initial02Closed;
    goto transitionFiring
  };
  main:
  current_event = empty;
  sender = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,sender,parameters[0],ack_out
  fi;
  atomic {
    if
    :: state0 == Closed && current_event == send_open ->
       if
       :: current_event <= 3 ->
          observer!RECEIVE,sender,this,current_event,parameters[0]
       :: else
       fi;
       state0 = empty;
       state0 = Open;
       goto main
    :: state0 == Closed && current_event == call_check ->
       if
       :: current_event <= 3 ->
          observer!RECEIVE,sender,this,current_event,parameters[0]
       :: else
       fi;
       ack_out!1;
       state0 = empty;
       state0 = Closed;
       goto main
    :: state0 == Open && current_event == send_close ->
       if
       :: current_event <= 3 ->
          observer!RECEIVE,sender,this,current_event,parameters[0]
       :: else
       fi;
       state0 = empty;
       state0 = Closed;
       goto main
    :: else
    fi;
    top_label:
    skip;
    if
    :: current_event == call_check ->
       ack_out!1
    :: else
    fi;
    if
    :: current_event <= 3 ->
       observer!RECEIVE,sender,this,current_event,parameters[0]
    :: else
    fi;
    transitionFiring:
    if
    :: state0_transition == Initial02Closed ->
       state0_transition = empty;
       state0 = empty;
       state0 = Closed
    :: else
    fi;
    goto main
  };
  end_machine0:
  observer!TERMINATION,this,empty,empty,0;
  end_machine:
  success
}

proctype Tester() {
  chan ack_in = [0] of {bit};

  bit direction;
  byte sender;
  int behavioural;
  int arguments[max_parameters];
  byte receiver;

  nc_t_1:
  event_queues[obj_ui-1]!send_small,empty,empty,ack_in;
  nc_t_2:
  if
  :: observer?direction,sender,receiver,behavioural,arguments[0] ->
     if
     :: direction == RECEIVE && sender == empty && behavioural == send_small && receiver == obj_ui ->
        goto nc_t_3
     :: direction == SEND && behavioural == send_small ->
        goto nc_t_2
     :: direction == RECEIVE && behavioural == send_small ->
        goto nc_t_2
     :: direction == SEND && behavioural == send_brew ->
        goto nc_t_2
     :: direction == RECEIVE && behavioural == send_brew ->
        goto nc_t_2
     :: else ->
        assert(false)
     fi
  fi;
  nc_t_3:
  if
  :: observer?direction,sender,receiver,behavioural,arguments[0] ->
     if
     :: direction == SEND && sender == obj_ui && behavioural == send_brew && receiver == obj_ctrl ->
        goto acceptAll
     :: direction == SEND && behavioural == send_small ->
        goto nc_t_3
     :: direction == RECEIVE && behavioural == send_small ->
        goto nc_t_3
     :: direction == SEND && behavioural == send_brew ->
        goto nc_t_3
     :: direction == RECEIVE && behavioural == send_brew ->
        goto nc_t_3
     :: else ->
        assert(false)
     fi
  fi;
  acceptAll:
  if
  :: 0 == 0 ->
     goto acceptAll
  fi
}

init {
  atomic {
    processIds[obj_ui-1] = run UserInterface(event_queues[obj_ui-1], obj_ui, empty, obj_led0, obj_led1, obj_led2, obj_ctrl);
    processIds[obj_grinder-1] = run Grinder(event_queues[obj_grinder-1], obj_grinder, obj_ctrl);
    processIds[obj_led1-1] = run LED(event_queues[obj_led1-1], obj_led1);
    processIds[obj_ctrl-1] = run Control(event_queues[obj_ctrl-1], obj_ctrl, obj_grinder, obj_heater, obj_waterTank, empty, obj_ui);
    processIds[obj_led2-1] = run LED(event_queues[obj_led2-1], obj_led2);
    processIds[obj_led0-1] = run LED(event_queues[obj_led0-1], obj_led0);
    processIds[obj_heater-1] = run Heater(event_queues[obj_heater-1], obj_heater, obj_ctrl);
    processIds[obj_waterTank-1] = run WaterTank(event_queues[obj_waterTank-1], obj_waterTank, obj_ctrl);
    run Tester()
  }
}
