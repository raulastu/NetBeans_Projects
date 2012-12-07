
go(Start, Goal, Obst) :-
empty_queue(Empty_open_queue),
enqueue([Start, nil], Empty_open_queue, Open_queue),
%empty_set(Closed_set),
Closed_set = [[Start, nil]],
path(Open_queue, Closed_set, Goal, Obst).

path(Open_queue, _, _, _) :-
empty_queue(Open_queue),
write('no solution found.'),
printAndSave([],[]).

path(Open_queue, Closed_set, Goal, _) :-
dequeue([State, Parent], Open_queue, _),
my_equal(State, Goal),
write('Solution path is :'), nl,
%printsolution([State, Parent], Closed_set).
printAndSave([State, Parent], Closed_set).

path(Open_queue, Closed_set, Goal, Obst) :-
dequeue([State, Parent], Open_queue, Rest_open_queue),
get_children(State, Rest_open_queue, Closed_set, Children, Obst),
add_list_to_queue(Children, Rest_open_queue, New_open_queue),
%add_to_set([[State, Parent]], Closed_set, New_closed_set),
my_union([[State, Parent]], Closed_set, New_closed_set),
path(New_open_queue, New_closed_set, Goal, Obst),!.

get_children(State, Rest_open_queue, Closed_set, Children, Obst) :-
(bagof(Child,moves(State, Rest_open_queue, Closed_set, Child, Obst),Children);Children = []).

moves(State, Rest_open_queue, Closed_set, [Next, State], Obst) :-
move(State, Next, Obst),
not(member_queue([Next, _], Rest_open_queue)),
not(member_set([Next, _], Closed_set, _)).

printAndSave([],[]):-
open('MyRCBotObst.sol', write, Stream),             %%Cambie la ruta del archivo aca
write(Stream, 'No hay solucion'),
close(Stream).

printAndSave([State, Parent], Closed_set):-
open('MyRCBotObst.sol', write, Stream),                %%Cambie la ruta del archivo aca
printsolution([State, Parent], Closed_set, Stream),
close(Stream).

printsolution([[Xb, Yb, Xm, Ym, _, _], nil], _, Stream) :-
write(Stream, (Xb, Yb, Xm, Ym)),
nl(Stream),
write([Xb, Yb, Xm, Ym]),nl.

printsolution([State, Parent], Closed_set, Stream) :-
member_set([Parent, _], Closed_set, Grandparent),
printsolution([Parent, Grandparent], Closed_set, Stream),
my_write(State, Stream), nl.

my_write([Xb, Yb, Xm, Ym, _, _], Stream) :-
write(Stream, (Xb,Yb,Xm,Ym)),
nl(Stream),
write([Xb, Yb, Xm, Ym]).

my_equal([_, _, Xm, Ym, _, _], [Z, W]) :-
Xm = Z,
Ym = W.

%%%%%%%%%%


empty_queue([]).

enqueue(E, [], [E]).

enqueue(E, [H | T], [H | Tnew]) :-
enqueue(E, T, Tnew).

dequeue(E, [E | T], T).

dequeue(E, [E | _], _).

member_queue(Element, Queue) :-
member(Element, Queue).

add_list_to_queue(List, Queue, Newqueue) :-
append(Queue, List, Newqueue).


%%%%%%%%%%%%%%%


empty_set([]).

member_set([State, _], [[State, Y] | _], Y).
member_set([State, _], [_ | T], Y) :-
member_set([State, _], T, Y).

add_if_not_in_set(X, S, S) :-
member_set(X, S, _),!.

add_if_not_in_set(X, S, [X | S]).

my_union([], S, S).

my_union([H | T], S, S_new) :-
my_union(T, S, S2),
add_if_not_in_set(H, S2, S_new),!.

my_subset([], _).

my_subset([H | T], S) :-
member_set(H, S, _),
my_subset(T, S).

equal_set(S1, S2) :-
my_subset(S1, S2),
my_subset(S2, S1).

%%%%%%%%%%Move Rules%%%%%%%%%%%%%%%%%%%%%


%Mover Arriba
move([Xb, Yb, Xm, Ym, Xmax, Ymax], [NewXb, Yb, Xm, Ym, Xmax, Ymax], Obst) :-
Xb -1 >= 0,
C is Xb - 1,
(C,Yb) \== (Xm,Ym),
NewXb is Xb - 1,
not(member_obst(NewXb, Yb, Obst)).

%Bot Mover Abajo
move([Xb, Yb, Xm, Ym, Xmax, Ymax], [NewXb, Yb, Xm, Ym, Xmax, Ymax], Obst) :-
Xb < Xmax ,
C is Xb + 1,
(C, Yb) \== (Xm,Ym),
NewXb is Xb + 1,
not(member_obst(NewXb, Yb, Obst)).

%Bot Mover Izquierda
move([Xb, Yb, Xm, Ym, Xmax, Ymax], [Xb, NewYb, Xm, Ym, Xmax, Ymax], Obst) :-
Yb > 0 ,
C is Yb - 1,
(Xb, C) \== (Xm,Ym),
NewYb is Yb - 1,
not(member_obst(Xb, NewYb, Obst)).

%Bot Mover Derecha
move([Xb, Yb, Xm, Ym, Xmax, Ymax], [Xb, NewYb, Xm, Ym, Xmax, Ymax],Obst) :-
Yb < Ymax ,
C is Yb + 1,
(Xb, C) \== (Xm,Ym),
NewYb is Yb + 1,
not(member_obst(Xb, NewYb, Obst)).


%Movil Mover Arriba
move([Xb, Yb, Xm, Ym, Xmax, Ymax], [NewXb, Yb, NewXm, Ym, Xmax, Ymax],Obst) :-
Xm > 0,
Ym =:= Yb,
Xm + 1 =:= Xb,
NewXb is Xb - 1,
NewXm is Xm - 1,
not(member_obst(NewXm, Ym, Obst)).

%Movil Mover Abajo
move([Xb, Yb, Xm, Ym, Xmax, Ymax], [NewXb, Yb, NewXm, Ym, Xmax, Ymax], Obst) :-
Xm < Xmax,
Ym =:= Yb,
Xm - 1 =:= Xb,
NewXb is Xb + 1,
NewXm is Xm + 1,
not(member_obst(NewXm, Ym, Obst)).

%Movil Mover Derecha
move([Xb, Yb, Xm, Ym, Xmax, Ymax], [Xb, NewYb, Xm, NewYm, Xmax, Ymax], Obst) :-
Ym < Ymax,
Xm =:= Xb,
Ym - 1 =:= Yb,
NewYb is Yb + 1,
NewYm is Ym + 1,
not(member_obst(Xm,NewYm,Obst)).

%Movil Mover Izquierda
move([Xb, Yb, Xm, Ym, Xmax, Ymax], [Xb, NewYb, Xm, NewYm, Xmax, Ymax], Obst) :-
Ym > 0,
Xm =:= Xb,
Ym + 1 =:= Yb,
NewYb is Yb - 1,
NewYm is Ym - 1,
not(member_obst(Xm, NewYm, Obst)).

member_obst(X,Y, [[X,Y]|_]).
member_obst(X,Y, [[_,_]|Ts]):-member_obst(X,Y,Ts).



