#define Initial1 1
#define Initial12WaitForRequest 1
#define empty 0
#define completion_queue_size 2
#define WaitForRequest 2
#define call_pay 2
#define WaitForRequest2PaymentStateMachine 2
#define send_done 1
#define WaitForRequest2FinalState1 3
#define PaymentStateMachine 3
#define addingDeliveryCost 4
#define completion_Region1region0_PaymentStateMachine_addi 3
#define cmplAddingDeliveryCost 2
#define PaidWithoutDeliveryCost 5
#define cmplPaidWithoutDeliveryCost 2
#define completion_Region1region0_PaymentStateMachine_Paid 4
#define PaidWithDeliveryCost 6
#define cmplPaidWithDeliveryCost 2
#define completion_Region1region0_PaymentStateMachine_Paid_G0 5
#define PaidWithoutDeliveryCost2FinalState1 4
#define PaidWithDeliveryCost2FinalState1 5
#define completion_Region1region0_PaymentStateMachine 6
#define cmplPaymentStateMachine 1
#define PaymentStateMachine2WaitForRequest 6
#define Initial1_G0 7
#define Initial12addingDeliveryCost 7
#define FinalState1 8
#define number_objects 0
#define queue_size 5
#define send_transactionCompleted 1
#define SEND 0
#define FinalState1_G0 9
#define success 0
#define Initial1_G1 1
#define Initial12ChoiceEntry 1
#define ChoiceEntry 2
#define completion_Region1region0_ChoiceEntry 2
#define cmplChoiceEntry 1
#define ChoiceEntry2proceedToPayment_ 2
#define PaymentRequestSent 3
#define PaymentRequestSent2FinalState1 3
#define FinalState1_G1 4
#define proceedToPayment_2FinalState1 4
#define proceedToPayment_ 5
#define proceedToPayment_2PaymentRequestSent 5
#define Initial1_G2 1
#define Initial12Empty 1
#define Empty 2
#define completion_Region1region0_Empty 1
#define cmplEmpty 1
#define ItemsAdded 3
#define cmplItemsAdded 1
#define completion_Region1region0_ItemsAdded 2
#define ProcceedToPayment 4
#define cmplProcceedToPayment 1
#define completion_Region1region0_ProcceedToPayment 3
#define ProcceedToPayment2FinalState1 2
#define FinalState1_G2 5
#define Initial1_G3 1
#define Initial12Supplied 1
#define Supplied 2
#define completion_Region1region0_Supplied 1
#define cmplSupplied 1
#define Stored 3
#define cmplStored 1
#define completion_Region1region0_Stored 2
#define Stored2FinalState1 2
#define FinalState1_G3 4
#define Initial1_G4 1
#define Initial12PaymentMethodSelected 1
#define PaymentMethodSelected 2
#define completion_Region1region0_PaymentMethodSelected 1
#define cmplPaymentMethodSelected 1
#define PaymentDetailsInserted 3
#define cmplPaymentDetailsInserted 1
#define completion_Region1region0_PaymentDetailsInserted 2
#define PaymentDetailsInserted2FinalState1 2
#define FinalState1_G4 4
#define Initial1_G5 1
#define Initial12OrderCreated 1
#define OrderCreated 2
#define completion_Region1region0_OrderCreated 1
#define cmplOrderCreated 1
#define OrderShipped 3
#define cmplOrderShipped 1
#define completion_Region1region0_OrderShipped 2
#define OrderDelivered 4
#define completion_Region1region0_OrderDelivered 3
#define cmplOrderDelivered 1
#define OrderDelivered2FinalState1 2
#define FinalState1_G5 5
#define Initial1_G6 1
#define Initial12Idle 1
#define Idle 2
#define call_login 1
#define Logging 3
#define cmplLogging 1
#define completion_Region1region0_Logging 2
#define Logging2Choice1 2
#define LoginDenied 4
#define completion_Region1region0_LoginDenied 3
#define cmplLoginDenied 1
#define LoginDenied2FinalState1 3
#define LoggedSuccessfully 5
#define completion_Region1region0_LoggedSuccessfully 4
#define cmplLoggedSuccessfully 1
#define LoggedSuccessfully2Choice2 4
#define LoggedAsCustomer 6
#define completion_Region1region0_LoggedAsCustomer 5
#define cmplLoggedAsCustomer 1
#define LoggedAsCustomer2FinalState1 5
#define LoggedAsEmployee 7
#define completion_Region1region0_LoggedAsEmployee 6
#define cmplLoggedAsEmployee 1
#define LoggedAsEmployee2FinalState1 6
#define Choice22LoggedAsCustomer 7
#define Choice1 8
#define Choice12LoginDenied 8
#define Choice12LoggedSuccessfully 9
#define Choice12Logging 10
#define send_loginDenied 2
#define Choice2 9
#define Choice22LoggedAsEmployee 11
#define FinalState1_G6 10
#define send_loggedSuccessfully 1
#define Initial1_G7 1
#define Initial12ItemSelected 1
#define ItemSelected 2
#define completion_Region1region0_ItemSelected 1
#define cmplItemSelected 1
#define ItemAddedToShoppingCart 3
#define cmplItemAddedToShoppingCart 1
#define completion_Region1region0_ItemAddedToShoppingCart 2
#define ItemAddedToShoppingCart2FinalState1 2
#define FinalState1_G7 4
#define Initial1_G8 1
#define Initial12AuthorizationQuerySent 1
#define AuthorizationQuerySent 2
#define LoggedSuccessfully_G0 3
#define cmplLoggedSuccessfully_G0 1
#define completion_Region1region0_LoggedSuccessfully_G0 3
#define LoginDenied_G0 4
#define cmplLoginDenied_G0 1
#define completion_Region1region0_LoginDenied_G0 4
#define LoggedSuccessfully2FinalState1 2
#define LoginDenied2FinalState1_G0 3
#define FinalState1_G8 5

chan event_queues[number_objects] = [queue_size] of {byte, chan};

bool CustomerProfile_primeCustomer;
int Role_tries;
int CustomerProfile_totalPriceOfItems;
bool Customer_proceedToPayment;
bool CustomerProfile_freeDelivery;
int CustomerProfile_billingAddress;
bool Role_validEntry;
bool WebUser_performLoginAction;
bool WebUser_asCustomer;

proctype Order(chan event_queue; bit this) {
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  byte state0_transition;
  byte state0;
  byte current_event;
  bool completed[2];

  xr event_queue;
  atomic {
    state0 = Initial1_G5;
    state0_transition = Initial12OrderCreated;
    goto transitionFiring
  };
  main:
  current_event = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,ack_out
  fi;
  atomic {
    if
    :: state0 == OrderCreated && current_event == completion_Region1region0_OrderCreated && completed[cmplOrderCreated] == true ->
       completed[cmplOrderCreated] = false;
       state0 = empty;
       state0 = OrderShipped;
       completed[cmplOrderShipped] = true;
       internal_queue!completion_Region1region0_OrderShipped;
       goto main
    :: state0 == OrderDelivered && current_event == completion_Region1region0_OrderDelivered && completed[cmplOrderDelivered] == true ->
       state0_transition = OrderDelivered2FinalState1;
       goto Region1region0_label
    :: state0 == OrderShipped && current_event == completion_Region1region0_OrderShipped && completed[cmplOrderShipped] == true ->
       completed[cmplOrderShipped] = false;
       state0 = empty;
       state0 = OrderDelivered;
       completed[cmplOrderDelivered] = true;
       internal_queue!completion_Region1region0_OrderDelivered;
       goto main
    :: else
    fi;
    Region1region0_label:
    skip;
    transitionFiring:
    if
    :: state0_transition == Initial12OrderCreated ->
       state0_transition = empty;
       state0 = empty;
       state0 = OrderCreated;
       completed[cmplOrderCreated] = true;
       internal_queue!completion_Region1region0_OrderCreated
    :: state0_transition == OrderDelivered2FinalState1 ->
       state0_transition = empty;
       completed[cmplOrderDelivered] = false;
       state0 = empty;
       state0 = FinalState1_G5
    :: else
    fi;
    if
    :: state0_transition != empty ->
       goto transitionFiring
    :: else
    fi;
    if
    :: state0 != FinalState1_G5 ->
       goto main
    :: else ->
       goto end_machine
    fi
  };
  end_machine:
  success
}

proctype Role(chan event_queue; bit this; bit initialiser_webuser0) {
  chan ack_in = [0] of {bit};
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  bit Logging_chosen;
  byte state0_transition;
  byte state0;
  bit webuser;
  byte current_event;
  bool completed[2];

  xr event_queue;
  atomic {
    webuser = initialiser_webuser0;
    state0 = Initial1_G6;
    state0_transition = Initial12Idle;
    Logging_chosen = true;
    goto transitionFiring
  };
  main:
  current_event = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,ack_out
  fi;
  atomic {
    if
    :: state0 == Idle && current_event == call_login ->
       ack_out!1;
       state0 = empty;
       Role_tries = 0;
       state0 = Logging;
       completed[cmplLogging] = true;
       internal_queue!completion_Region1region0_Logging;
       goto main
    :: state0 == Logging && current_event == completion_Region1region0_Logging && completed[cmplLogging] == true ->
       state0_transition = Logging2Choice1;
       Logging_chosen = true;
       goto Region1region0_label
    :: state0 == LoginDenied && current_event == completion_Region1region0_LoginDenied && completed[cmplLoginDenied] == true ->
       state0_transition = LoginDenied2FinalState1;
       Logging_chosen = true;
       goto Region1region0_label
    :: state0 == LoggedSuccessfully && current_event == completion_Region1region0_LoggedSuccessfully && completed[cmplLoggedSuccessfully] == true ->
       state0_transition = LoggedSuccessfully2Choice2;
       Logging_chosen = true;
       goto Region1region0_label
    :: state0 == LoggedAsCustomer && current_event == completion_Region1region0_LoggedAsCustomer && completed[cmplLoggedAsCustomer] == true ->
       state0_transition = LoggedAsCustomer2FinalState1;
       Logging_chosen = true;
       goto Region1region0_label
    :: state0 == LoggedAsEmployee && current_event == completion_Region1region0_LoggedAsEmployee && completed[cmplLoggedAsEmployee] == true ->
       state0_transition = LoggedAsEmployee2FinalState1;
       Logging_chosen = true;
       goto Region1region0_label
    :: else
    fi;
    Region1region0_label:
    skip;
    if
    :: current_event == call_login ->
       ack_out!1
    :: else
    fi;
    transitionFiring:
    if
    :: state0_transition == Choice22LoggedAsCustomer ->
       state0_transition = empty;
       state0 = empty;
       state0 = LoggedAsCustomer;
       completed[cmplLoggedAsCustomer] = true;
       internal_queue!completion_Region1region0_LoggedAsCustomer
    :: state0_transition == Initial12Idle ->
       state0_transition = empty;
       state0 = empty;
       state0 = Idle
    :: state0_transition == Logging2Choice1 ->
       state0_transition = empty;
       completed[cmplLogging] = false;
       state0 = empty;
       Role_tries = Role_tries+1;
       state0 = Choice1;
       if
       :: ((Role_validEntry == false) == false -> false : Role_tries > 2) ->
          state0_transition = Choice12LoginDenied;
          Logging_chosen = true
       :: ((Role_validEntry == true) == false -> false : Role_tries < 3) ->
          state0_transition = Choice12LoggedSuccessfully;
          Logging_chosen = true
       :: (((Role_validEntry != false) == true -> true : Role_tries <= 2) == false -> false : ((Role_validEntry != true) == true -> true : Role_tries >= 3)) ->
          state0_transition = Choice12Logging;
          Logging_chosen = true
       :: else ->
          assert(false)
       fi
    :: state0_transition == Choice12LoginDenied ->
       state0_transition = empty;
       state0 = empty;
       event_queues[webuser-1]!send_loginDenied,ack_in;
       state0 = LoginDenied;
       completed[cmplLoginDenied] = true;
       internal_queue!completion_Region1region0_LoginDenied
    :: state0_transition == Choice12Logging ->
       state0_transition = empty;
       state0 = empty;
       state0 = Logging;
       completed[cmplLogging] = true;
       internal_queue!completion_Region1region0_Logging
    :: state0_transition == LoggedSuccessfully2Choice2 ->
       state0_transition = empty;
       completed[cmplLoggedSuccessfully] = false;
       state0 = empty;
       state0 = Choice2;
       if
       :: WebUser_asCustomer == true ->
          state0_transition = Choice22LoggedAsCustomer;
          Logging_chosen = true
       :: WebUser_asCustomer != true ->
          state0_transition = Choice22LoggedAsEmployee;
          Logging_chosen = true
       :: else ->
          assert(false)
       fi
    :: state0_transition == LoginDenied2FinalState1 ->
       state0_transition = empty;
       completed[cmplLoginDenied] = false;
       state0 = empty;
       state0 = FinalState1_G6
    :: state0_transition == LoggedAsEmployee2FinalState1 ->
       state0_transition = empty;
       completed[cmplLoggedAsEmployee] = false;
       state0 = empty;
       state0 = FinalState1_G6
    :: state0_transition == Choice22LoggedAsEmployee ->
       state0_transition = empty;
       state0 = empty;
       state0 = LoggedAsEmployee;
       completed[cmplLoggedAsEmployee] = true;
       internal_queue!completion_Region1region0_LoggedAsEmployee
    :: state0_transition == Choice12LoggedSuccessfully ->
       state0_transition = empty;
       state0 = empty;
       event_queues[webuser-1]!send_loggedSuccessfully,ack_in;
       state0 = LoggedSuccessfully;
       completed[cmplLoggedSuccessfully] = true;
       internal_queue!completion_Region1region0_LoggedSuccessfully
    :: state0_transition == LoggedAsCustomer2FinalState1 ->
       state0_transition = empty;
       completed[cmplLoggedAsCustomer] = false;
       state0 = empty;
       state0 = FinalState1_G6
    :: else
    fi;
    if
    :: state0_transition != empty ->
       goto transitionFiring
    :: else
    fi;
    Logging_chosen = false;
    if
    :: state0 != FinalState1_G6 ->
       goto main
    :: else ->
       goto end_machine
    fi
  };
  end_machine:
  success
}

proctype Payment(chan event_queue; bit this) {
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  byte state0_transition;
  byte state0;
  byte current_event;
  bool completed[2];

  xr event_queue;
  atomic {
    state0 = Initial1_G4;
    state0_transition = Initial12PaymentMethodSelected;
    goto transitionFiring
  };
  main:
  current_event = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,ack_out
  fi;
  atomic {
    if
    :: state0 == PaymentMethodSelected && current_event == completion_Region1region0_PaymentMethodSelected && completed[cmplPaymentMethodSelected] == true ->
       completed[cmplPaymentMethodSelected] = false;
       state0 = empty;
       state0 = PaymentDetailsInserted;
       completed[cmplPaymentDetailsInserted] = true;
       internal_queue!completion_Region1region0_PaymentDetailsInserted;
       goto main
    :: state0 == PaymentDetailsInserted && current_event == completion_Region1region0_PaymentDetailsInserted && completed[cmplPaymentDetailsInserted] == true ->
       state0_transition = PaymentDetailsInserted2FinalState1;
       goto Region1region0_label
    :: else
    fi;
    Region1region0_label:
    skip;
    transitionFiring:
    if
    :: state0_transition == Initial12PaymentMethodSelected ->
       state0_transition = empty;
       state0 = empty;
       state0 = PaymentMethodSelected;
       completed[cmplPaymentMethodSelected] = true;
       internal_queue!completion_Region1region0_PaymentMethodSelected
    :: state0_transition == PaymentDetailsInserted2FinalState1 ->
       state0_transition = empty;
       completed[cmplPaymentDetailsInserted] = false;
       state0 = empty;
       state0 = FinalState1_G4
    :: else
    fi;
    if
    :: state0_transition != empty ->
       goto transitionFiring
    :: else
    fi;
    if
    :: state0 != FinalState1_G4 ->
       goto main
    :: else ->
       goto end_machine
    fi
  };
  end_machine:
  success
}

proctype ShoppingCart(chan event_queue; bit this) {
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  byte state0_transition;
  byte state0;
  byte current_event;
  bool completed[2];

  xr event_queue;
  atomic {
    state0 = Initial1_G2;
    state0_transition = Initial12Empty;
    goto transitionFiring
  };
  main:
  current_event = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,ack_out
  fi;
  atomic {
    if
    :: state0 == Empty && current_event == completion_Region1region0_Empty && completed[cmplEmpty] == true ->
       completed[cmplEmpty] = false;
       state0 = empty;
       state0 = ItemsAdded;
       completed[cmplItemsAdded] = true;
       internal_queue!completion_Region1region0_ItemsAdded;
       goto main
    :: state0 == ItemsAdded && current_event == completion_Region1region0_ItemsAdded && completed[cmplItemsAdded] == true ->
       completed[cmplItemsAdded] = false;
       state0 = empty;
       state0 = ProcceedToPayment;
       completed[cmplProcceedToPayment] = true;
       internal_queue!completion_Region1region0_ProcceedToPayment;
       goto main
    :: state0 == ProcceedToPayment && current_event == completion_Region1region0_ProcceedToPayment && completed[cmplProcceedToPayment] == true ->
       state0_transition = ProcceedToPayment2FinalState1;
       goto Region1region0_label
    :: else
    fi;
    Region1region0_label:
    skip;
    transitionFiring:
    if
    :: state0_transition == ProcceedToPayment2FinalState1 ->
       state0_transition = empty;
       completed[cmplProcceedToPayment] = false;
       state0 = empty;
       state0 = FinalState1_G2
    :: state0_transition == Initial12Empty ->
       state0_transition = empty;
       state0 = empty;
       state0 = Empty;
       completed[cmplEmpty] = true;
       internal_queue!completion_Region1region0_Empty
    :: else
    fi;
    if
    :: state0_transition != empty ->
       goto transitionFiring
    :: else
    fi;
    if
    :: state0 != FinalState1_G2 ->
       goto main
    :: else ->
       goto end_machine
    fi
  };
  end_machine:
  success
}

proctype Customer(chan event_queue; bit this; bit initialiser_custProf0) {
  chan ack_in = [0] of {bit};
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  byte state0_transition;
  byte state0;
  bit ChoiceEntry_chosen;
  byte current_event;
  bool completed[2];
  bit custProf;

  xr event_queue;
  atomic {
    custProf = initialiser_custProf0;
    state0 = Initial1_G1;
    state0_transition = Initial12ChoiceEntry;
    ChoiceEntry_chosen = true;
    goto transitionFiring
  };
  main:
  current_event = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,ack_out
  fi;
  atomic {
    if
    :: state0 == ChoiceEntry && current_event == completion_Region1region0_ChoiceEntry && completed[cmplChoiceEntry] == true ->
       state0_transition = ChoiceEntry2proceedToPayment_;
       ChoiceEntry_chosen = true;
       goto Region1region0_label
    :: state0 == PaymentRequestSent && current_event == send_transactionCompleted ->
       state0_transition = PaymentRequestSent2FinalState1;
       ChoiceEntry_chosen = true;
       goto Region1region0_label
    :: else
    fi;
    Region1region0_label:
    skip;
    transitionFiring:
    if
    :: state0_transition == PaymentRequestSent2FinalState1 ->
       state0_transition = empty;
       state0 = empty;
       event_queues[custProf-1]!send_done,ack_in;
       state0 = FinalState1_G1
    :: state0_transition == proceedToPayment_2FinalState1 ->
       state0_transition = empty;
       state0 = empty;
       event_queues[custProf-1]!send_done,ack_in;
       state0 = FinalState1_G1
    :: state0_transition == ChoiceEntry2proceedToPayment_ ->
       state0_transition = empty;
       completed[cmplChoiceEntry] = false;
       state0 = empty;
       state0 = proceedToPayment_;
       if
       :: Customer_proceedToPayment != true ->
          state0_transition = proceedToPayment_2FinalState1;
          ChoiceEntry_chosen = true
       :: Customer_proceedToPayment == true ->
          state0_transition = proceedToPayment_2PaymentRequestSent;
          ChoiceEntry_chosen = true
       :: else ->
          assert(false)
       fi
    :: state0_transition == proceedToPayment_2PaymentRequestSent ->
       state0_transition = empty;
       state0 = empty;
       event_queues[custProf-1]!call_pay,ack_in;
       ack_in?_;
       state0 = PaymentRequestSent
    :: state0_transition == Initial12ChoiceEntry ->
       state0_transition = empty;
       state0 = empty;
       state0 = ChoiceEntry;
       completed[cmplChoiceEntry] = true;
       internal_queue!completion_Region1region0_ChoiceEntry
    :: else
    fi;
    if
    :: state0_transition != empty ->
       goto transitionFiring
    :: else
    fi;
    ChoiceEntry_chosen = false;
    if
    :: state0 != FinalState1_G1 ->
       goto main
    :: else ->
       goto end_machine
    fi
  };
  end_machine:
  success
}

proctype Product(chan event_queue; bit this) {
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  byte state0_transition;
  byte state0;
  byte current_event;
  bool completed[2];

  xr event_queue;
  atomic {
    state0 = Initial1_G3;
    state0_transition = Initial12Supplied;
    goto transitionFiring
  };
  main:
  current_event = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,ack_out
  fi;
  atomic {
    if
    :: state0 == Supplied && current_event == completion_Region1region0_Supplied && completed[cmplSupplied] == true ->
       completed[cmplSupplied] = false;
       state0 = empty;
       state0 = Stored;
       completed[cmplStored] = true;
       internal_queue!completion_Region1region0_Stored;
       goto main
    :: state0 == Stored && current_event == completion_Region1region0_Stored && completed[cmplStored] == true ->
       state0_transition = Stored2FinalState1;
       goto Region1region0_label
    :: else
    fi;
    Region1region0_label:
    skip;
    transitionFiring:
    if
    :: state0_transition == Stored2FinalState1 ->
       state0_transition = empty;
       completed[cmplStored] = false;
       state0 = empty;
       state0 = FinalState1_G3
    :: state0_transition == Initial12Supplied ->
       state0_transition = empty;
       state0 = empty;
       state0 = Supplied;
       completed[cmplSupplied] = true;
       internal_queue!completion_Region1region0_Supplied
    :: else
    fi;
    if
    :: state0_transition != empty ->
       goto transitionFiring
    :: else
    fi;
    if
    :: state0 != FinalState1_G3 ->
       goto main
    :: else ->
       goto end_machine
    fi
  };
  end_machine:
  success
}

proctype LineItem(chan event_queue; bit this) {
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  byte state0_transition;
  byte state0;
  byte current_event;
  bool completed[2];

  xr event_queue;
  atomic {
    state0 = Initial1_G7;
    state0_transition = Initial12ItemSelected;
    goto transitionFiring
  };
  main:
  current_event = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,ack_out
  fi;
  atomic {
    if
    :: state0 == ItemSelected && current_event == completion_Region1region0_ItemSelected && completed[cmplItemSelected] == true ->
       completed[cmplItemSelected] = false;
       state0 = empty;
       state0 = ItemAddedToShoppingCart;
       completed[cmplItemAddedToShoppingCart] = true;
       internal_queue!completion_Region1region0_ItemAddedToShoppingCart;
       goto main
    :: state0 == ItemAddedToShoppingCart && current_event == completion_Region1region0_ItemAddedToShoppingCart && completed[cmplItemAddedToShoppingCart] == true ->
       state0_transition = ItemAddedToShoppingCart2FinalState1;
       goto Region1region0_label
    :: else
    fi;
    Region1region0_label:
    skip;
    transitionFiring:
    if
    :: state0_transition == Initial12ItemSelected ->
       state0_transition = empty;
       state0 = empty;
       state0 = ItemSelected;
       completed[cmplItemSelected] = true;
       internal_queue!completion_Region1region0_ItemSelected
    :: state0_transition == ItemAddedToShoppingCart2FinalState1 ->
       state0_transition = empty;
       completed[cmplItemAddedToShoppingCart] = false;
       state0 = empty;
       state0 = FinalState1_G7
    :: else
    fi;
    if
    :: state0_transition != empty ->
       goto transitionFiring
    :: else
    fi;
    if
    :: state0 != FinalState1_G7 ->
       goto main
    :: else ->
       goto end_machine
    fi
  };
  end_machine:
  success
}

proctype CustomerProfile(chan event_queue; bit this; bit initialiser_cust0) {
  chan ack_in = [0] of {bit};
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  byte state0_transition;
  byte state1_transition;
  byte state0;
  byte current_event;
  bool completed[3];
  bit cust;
  byte state1;

  xr event_queue;
  atomic {
    cust = initialiser_cust0;
    state0 = Initial1;
    state0_transition = Initial12WaitForRequest;
    goto transitionFiring
  };
  main:
  current_event = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,ack_out
  fi;
  atomic {
    if
    :: state0 == WaitForRequest && current_event == call_pay ->
       state0_transition = WaitForRequest2PaymentStateMachine;
       goto Region1region0_label
    :: state0 == WaitForRequest && current_event == send_done ->
       state0_transition = WaitForRequest2FinalState1;
       goto Region1region0_label
    :: else
    fi;
    if
    :: state0 == PaymentStateMachine ->
       if
       :: state1 == addingDeliveryCost && current_event == completion_Region1region0_PaymentStateMachine_addi && completed[cmplAddingDeliveryCost] == true && (((CustomerProfile_primeCustomer == true) == false -> false : CustomerProfile_totalPriceOfItems >= 35) == false -> false : CustomerProfile_billingAddress >= 56072) ->
          completed[cmplAddingDeliveryCost] = false;
          state1 = empty;
          CustomerProfile_freeDelivery = true;
          state1 = PaidWithoutDeliveryCost;
          completed[cmplPaidWithoutDeliveryCost] = true;
          internal_queue!completion_Region1region0_PaymentStateMachine_Paid;
          goto main
       :: state1 == addingDeliveryCost && current_event == completion_Region1region0_PaymentStateMachine_addi && completed[cmplAddingDeliveryCost] == true && (((CustomerProfile_primeCustomer != true) == true -> true : CustomerProfile_totalPriceOfItems < 35) == true -> true : CustomerProfile_billingAddress < 56072) ->
          completed[cmplAddingDeliveryCost] = false;
          state1 = empty;
          CustomerProfile_freeDelivery = false;
          CustomerProfile_totalPriceOfItems = 5;
          state1 = PaidWithDeliveryCost;
          completed[cmplPaidWithDeliveryCost] = true;
          internal_queue!completion_Region1region0_PaymentStateMachine_Paid_G0;
          goto main
       :: state1 == PaidWithoutDeliveryCost && current_event == completion_Region1region0_PaymentStateMachine_Paid && completed[cmplPaidWithoutDeliveryCost] == true ->
          state1_transition = PaidWithoutDeliveryCost2FinalState1;
          goto Region1region0_label
       :: state1 == PaidWithDeliveryCost && current_event == completion_Region1region0_PaymentStateMachine_Paid_G0 && completed[cmplPaidWithDeliveryCost] == true ->
          state1_transition = PaidWithDeliveryCost2FinalState1;
          goto Region1region0_label
       :: else
       fi
    :: else
    fi;
    if
    :: state0 == PaymentStateMachine && current_event == completion_Region1region0_PaymentStateMachine && completed[cmplPaymentStateMachine] == true ->
       state0_transition = PaymentStateMachine2WaitForRequest;
       goto Region1region0_label
    :: else
    fi;
    Region1region0_PaymentStateMachine_label:
    skip;
    Region1region0_label:
    skip;
    if
    :: current_event == call_pay ->
       ack_out!1
    :: else
    fi;
    transitionFiring:
    if
    :: state0_transition != empty ->
       if
       :: state0_transition == WaitForRequest2PaymentStateMachine ->
          state0_transition = empty;
          state0 = empty;
          CustomerProfile_freeDelivery = false;
          state0 = PaymentStateMachine;
          state1 = Initial1_G0;
          state1_transition = Initial12addingDeliveryCost
       :: state0_transition == PaymentStateMachine2WaitForRequest ->
          state0_transition = empty;
          if
          :: state1 == addingDeliveryCost ->
             completed[cmplAddingDeliveryCost] = false;
             state1 = empty
          :: state1 == PaidWithoutDeliveryCost ->
             completed[cmplPaidWithoutDeliveryCost] = false;
             state1 = empty
          :: state1 == FinalState1 ->
             state1 = empty
          :: state1 == PaidWithDeliveryCost ->
             completed[cmplPaidWithDeliveryCost] = false;
             state1 = empty
          fi;
          completed[cmplPaymentStateMachine] = false;
          state0 = empty;
          event_queues[cust-1]!send_transactionCompleted,ack_in;
          state0 = WaitForRequest
       :: state0_transition == WaitForRequest2FinalState1 ->
          state0_transition = empty;
          state0 = empty;
          state0 = FinalState1_G0
       :: state0_transition == Initial12WaitForRequest ->
          state0_transition = empty;
          state0 = empty;
          state0 = WaitForRequest
       :: else
       fi
    :: else ->
       if
       :: state1_transition != empty ->
          if
          :: state1_transition == PaidWithoutDeliveryCost2FinalState1 ->
             state1_transition = empty;
             completed[cmplPaidWithoutDeliveryCost] = false;
             state1 = empty;
             state1 = FinalState1;
             completed[cmplPaymentStateMachine] = true;
             internal_queue!completion_Region1region0_PaymentStateMachine
          :: state1_transition == PaidWithDeliveryCost2FinalState1 ->
             state1_transition = empty;
             completed[cmplPaidWithDeliveryCost] = false;
             state1 = empty;
             state1 = FinalState1;
             completed[cmplPaymentStateMachine] = true;
             internal_queue!completion_Region1region0_PaymentStateMachine
          :: state1_transition == Initial12addingDeliveryCost ->
             state1_transition = empty;
             state1 = empty;
             state1 = addingDeliveryCost;
             completed[cmplAddingDeliveryCost] = true;
             internal_queue!completion_Region1region0_PaymentStateMachine_addi
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
    if
    :: state0 != FinalState1_G0 ->
       goto main
    :: else ->
       goto end_machine
    fi
  };
  end_machine:
  success
}

proctype WebUser(chan event_queue; bit this; bit initialiser_role0) {
  chan ack_in = [0] of {bit};
  chan ack_out;
  chan internal_queue = [completion_queue_size] of {byte};

  byte state0_transition;
  byte state0;
  bit role;
  byte current_event;
  bool completed[2];

  xr event_queue;
  atomic {
    role = initialiser_role0;
    state0 = Initial1_G8;
    if
    :: WebUser_performLoginAction == true ->
       state0_transition = Initial12AuthorizationQuerySent
    :: else ->
       assert(false)
    fi;
    goto transitionFiring
  };
  main:
  current_event = empty;
  if
  :: internal_queue?[current_event] ->
     internal_queue?current_event
  :: else ->
     event_queue?current_event,ack_out
  fi;
  atomic {
    if
    :: state0 == AuthorizationQuerySent && current_event == send_loggedSuccessfully ->
       state0 = empty;
       state0 = LoggedSuccessfully_G0;
       completed[cmplLoggedSuccessfully_G0] = true;
       internal_queue!completion_Region1region0_LoggedSuccessfully_G0;
       goto main
    :: state0 == AuthorizationQuerySent && current_event == send_loginDenied ->
       state0 = empty;
       state0 = LoginDenied_G0;
       completed[cmplLoginDenied_G0] = true;
       internal_queue!completion_Region1region0_LoginDenied_G0;
       goto main
    :: state0 == LoggedSuccessfully_G0 && current_event == completion_Region1region0_LoggedSuccessfully_G0 && completed[cmplLoggedSuccessfully_G0] == true ->
       state0_transition = LoggedSuccessfully2FinalState1;
       goto Region1region0_label
    :: state0 == LoginDenied_G0 && current_event == completion_Region1region0_LoginDenied_G0 && completed[cmplLoginDenied_G0] == true ->
       state0_transition = LoginDenied2FinalState1_G0;
       goto Region1region0_label
    :: else
    fi;
    Region1region0_label:
    skip;
    transitionFiring:
    if
    :: state0_transition == LoggedSuccessfully2FinalState1 ->
       state0_transition = empty;
       completed[cmplLoggedSuccessfully_G0] = false;
       state0 = empty;
       state0 = FinalState1_G8
    :: state0_transition == Initial12AuthorizationQuerySent ->
       state0_transition = empty;
       state0 = empty;
       event_queues[role-1]!call_login,ack_in;
       ack_in?_;
       state0 = AuthorizationQuerySent
    :: state0_transition == LoginDenied2FinalState1_G0 ->
       state0_transition = empty;
       completed[cmplLoginDenied_G0] = false;
       state0 = empty;
       state0 = FinalState1_G8
    :: else
    fi;
    if
    :: state0_transition != empty ->
       goto transitionFiring
    :: else
    fi;
    if
    :: state0 != FinalState1_G8 ->
       goto main
    :: else ->
       goto end_machine
    fi
  };
  end_machine:
  success
}

init {
  atomic {
    skip
  }
}
