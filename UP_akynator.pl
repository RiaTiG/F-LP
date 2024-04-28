%main()
main :-
    retractall(asked(_,_)),
    game(Question),
    !,
    nl,
    write('Your game is '), write(Question), write(.), nl.
main :-
    nl,
    write('The question cannot be solved.'), nl.


%question(+Object)
question(arcade):-
    query('You wished arcade?').

question(casual):-
    query('You wished a casual game?').

question(horror):-
    query('You wished a horror game?').

question(story):-
    query('You wished story game?').

question(shooter):-
    query('You wished a  shooter?').

question(platformer):-
    query('You wished a platformer?').
	
question(simulator):-
    query('You wished a simulator?').
	
question(rpg):-
    query('You wished a role-playing games?').

question(strategies):-
    query('You wished a strategies?').
	
%game(+Game)
game(resident_evil):-
    question(horror),
	question(shooter),
	question(story),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=3,!.

game(outlast):-
	question(horror),
    question(story),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=2,!.

game(dead_by_day_light):-
    question(horror),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=1,!.

game(team_fortress):-
    question(shooter),
	question(casual),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=2,!.
	
game(doom):-
    question(shooter),
    question(platformer),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=2,!.

game(escape_from_tarkov):-
	question(shooter),
    question(rpg),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=2,!.

game(xcom):-
	question(strategies),
    question(shooter),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=2,!.

game(call_of_duty):-
    question(shooter),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=1,!.

game(donkey_kong):-
	question(arcade),
    question(platformer),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=2,!.

game(galaga):-
    question(arcade),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=1,!.

game(diablo):-
	question(rpg),
    question(story),
	question(casual),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=3,!.

game(mario):-
    question(story),
	question(platformer),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=2,!.

game(undertale):-
	question(casual),
    question(rpg),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=2,!.

game(subway_surfers):-
	question(platformer),
    question(casual),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=2,!.

game(assasins_creed):-
    question(story),
    question(platformer),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=2,!.

game(sims):-
	question(casual),
    question(simulator),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=2,!.

game(factorio):-
	question(simulator),
    question(strategies),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=2,!.

game(civilisation):-
    question(strategies),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=1,!.
	
game(euro_track):-
    question(simulator),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=1,!.


game(portal):-
    question(platformer),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=1,!.
	
game(baldures_gate):-
    question(rpg),
    bagof(X,asked(X,y),L),
    length(L,A),
    A=1,!.
	

%query(+Prompt)
query(Prompt) :-
    (   asked(Prompt, Reply) -> true
    ;   nl, write(Prompt), write(' (y/n)? '),
        read(X),(X = y -> Reply = y ; Reply = n),
	assert(asked(Prompt, Reply))
    ),
    Reply = y.