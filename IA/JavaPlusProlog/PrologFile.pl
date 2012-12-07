
sum(A, B, C):- (C is A + B).

pertenece(X,[X|_]):-!.
pertenece(X,[_|Cola]) :- pertenece(X,Cola).


concatenar([],X2,X2):-!.
concatenar([T|H],X2, [T|Z]):- concatenar(H,X2,Z).

esprefijo([], [_]):-!.
esprefijo([T|H], [T|Z]):-esprefijo(H,Z).

essufijo(H,H):-!.
essufijo(H, [_|Z]):-essufijo(H,Z).


