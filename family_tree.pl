man(voeneg).
man(ratibor).
man(boguslav).
man(velerad).
man(duhovlad).
man(svyatoslav).
man(dobrozhir).
man(bogomil).
man(zlatomir).

woman(goluba).
woman(lubomila).
woman(bratislava).
woman(veslava).
woman(zhdana).
woman(bozhedara).
woman(broneslava).
woman(veselina).
woman(zdislava).

parent(voeneg,ratibor).
parent(voeneg,bratislava).
parent(voeneg,velerad).
parent(voeneg,zhdana).

parent(goluba,ratibor).
parent(goluba,bratislava).
parent(goluba,velerad).
parent(goluba,zhdana).

parent(ratibor,svyatoslav).
parent(ratibor,dobrozhir).
parent(lubomila,svyatoslav).
parent(lubomila,dobrozhir).

parent(boguslav,bogomil).
parent(boguslav,bozhedara).
parent(bratislava,bogomil).
parent(bratislava,bozhedara).

parent(velerad,broneslava).
parent(velerad,veselina).
parent(veslava,broneslava).
parent(veslava,veselina).

parent(duhovlad,zdislava).
parent(duhovlad,zlatomir).
parent(zhdana,zdislava).
parent(zhdana,zlatomir).

%men(); Предикат, кторый выводит всех мужчин.
men():- man(X), print(X), nl, fail.

%women(); Предикат, кторый выводит всех женщин.
women():- woman(X), print(X), nl, fail.

%children(+X); Предикат, который выводит всех детей X.
children(X):- parent(X,Y), print(Y), nl, fail.

%mother(?X, +Y); Предикат, который проверяет, является ли X матерью Y.
mother(X,Y):- woman(X), parent(X,Y).

%mother(+X); Предикат, который выводит мать X.
mother(X):- mother(Y,X), print(Y), nl, fail.

%brother(?X, +Y); Предикат,который проверяет, является ли X братом Y.
brother(X,Y):- man(X), man(Z), parent(Z,X), parent(Z,Y).

% brothers(+X); Предикат, который выводит всех братьев X.
brothers(X) :- brother(Y, X), X\=Y, print(Y), nl, fail.

% b_s(?X, +Y);Предикат, который проверяет, являются ли X и Y
% родными братом и сестрой или братьями или сестрами.
b_s(X,Y):- man(Z), parent(Z,X), parent(Z,Y).

%b_s(+X); Предикат, который выводит всех братьев или сестер X.
b_s(X):- b_s(Y,X), X\=Y, print(Y), nl, fail.

%daughter(?X, +Y); Предикат, который проверяет, является ли X дочерью Y.
daughter(X, Y):- parent(Y,X), woman(X).

%daughter(+X); Предикат, который выводит дочь X.
daughter(X):- daughter(Y,X), print(Y), nl, fail.

%husband(?X, +Y); Предикат, который проверяет, является ли X мужем Y. 
husband(X, Y):- man(X), parent(X,Z), parent(Y,Z).

%husband(+X); Предикат, который выводит мужа X.
husband(X):- husband(Y,X), print(Y).

%grand_da(?X, +Y); Предикат grand_da(X, Y), который проверяет, является ли X внучкой Y.
grand_da(X, Y):- woman(X), parent(Z,X), parent(Y, Z).

%grand_dats(+X); Предикат который выводит всех внучек X.
grand_dats(X):- grand_da(Y, X), print(Y), nl, fail.

%grand_ma_and_da(?X, +Y); Предикат который проверяет, являются ли X и Y
%бабушкой и внучкой или внучкой и бабушкой.
grand_ma_and_da(X,Y):- woman(X), grand_da(Y, X), grand_da(X, Y).

%niece(?X, +Y); Предикат, который проверяет, является ли X племянницей Y.
niece(X, Y):- woman(X), parent(Z, X), b_s(Y, Z).

%nieces(+X); Предикат, который выводит всех племянниц X.
nieces(X):- niece(Y,X), print(Y), nl, fail.