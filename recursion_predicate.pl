%Реализовать предикат max(+X,+Y,+Z,-U), где U максимальное из чисел X, Y и Z.
max(X, Y, Z, U) :- (X >= Y, X >= Z-> U = X); 
(Y >= X, Y >= Z -> U = Y); (Z >= X, Z >= Y -> U = Z).

%Реализовать предикат fact(N,X), где X – это факториал первого аргумента с
%помощью рекурсии вверх, рекурсии вниз.

%Рекурсия вверх fact(+N, ?X)
fact(0, 1).
fact(N, X) :- N > 0, N1 is N - 1, fact(N1, X1), X is N * X1.

%Рекурсия вниз fact_d(+N, ?X)
fact_d(N, X):- fact_d(N, 1, X).
fact_d(0, Mult, Mult):- !.
fact_d(N, Mult, X):- N > 0, N1 is N - 1, Mult1 is N * Mult, fact_d(N1, Mult1, X).
