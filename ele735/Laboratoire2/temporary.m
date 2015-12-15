%% Laboratoire 2
% 
% OPTIMISATION NUM�RIQUE 
%
% Auteurs :
%
% Antoine Langevin
%
% Jean-Marc Lina
%
% Ghyslain Gagnon
%
% AUT2015
%
%%

%% Pr�sentation du probl�me
%
% Il y a deux sources et un capteur dans le plan (x,z).
%%

%%
% 
% <<Sch�ma1.png>>
% 
%
% La distance sur l'axe des x entre les deux sources est dx1 = 0.7 km et la
% distance sur l'axe des x entre
% la deuxi�me source et le capteur est dx2 = 5 km. La hauteur des deux
% sources est hs = 0.5 km et la hauteur du capteur est hc = 0.3 km. La
% distance d1 est la distance s�parant la source 1 du capteur et d2 est
% la distance s�parant la source 2 du capteur. 
% 
% L'amplitude d'�mission de la premi�re source est As1 = 1 et celle de 
% la deuxi�me source est As2 = 1.
%
% L'onde �mise par les deux sources est un cosinus de m�me phase, dont la
% fr�quence est de 150 kHz.
%
%
%
%% Premi�re partie : Amplitude en un point sans r�flexion (2 points)
% 
% Note:
% Pour la premi�re et deuxi�me partie, il ne faut pas tenir compte des 
% ondes qui sont r�fl�chies sur le sol.
%
% L'objectif de cette premi�re partie est de trouver l'amplitude totale 
% des signaux que re�oit le capteur.
%%
% *a)* �crire un algorithme permettant de trouver la distance s�parant 
% deux points d'un m�me plan. *(0.5 point)*
%%

% Fonction permettant d'ex�cuter le laboratoire sous la forme d'une suite
% de fonctions
function Laboratoire_2
	partie1();
    partie2();
    partie3();
    partie4();
    % partie5();
end

% Fonction permettant de trouver la distance entre 
% deux points dans le plan (x,z).
%
% Function [distance] = calcul_distance(P1, P2)
%
% Arguments :
%   P1 : Coordonn�es du premier point [x,z]
%   P2 : Coordonn�es du deuxi�me point [x,z]
%
% Sortie :
%   distance : Distance entre le point P1 et P2
% Partie 1 - a)
function [distance] = calcule_distance(pt1, pt2)
	% On utilise la version matlab potentiellement plus rapide?
	% distance = sqrt( (pt2(1) - pt1(1))^2 + (pt2(2) - pt1(2))^2);
	[distance] = pdist2(pt1, pt2);
end

%% 
% *b)* Voici le d�but du script Matlab pour ce laboratoire.
%%

% Fonction permettant d'initialiser l'environnement.
%
% Partie 1 - B)
function initialise_variables_globales()

	% (R�-)initialisation de l'environnement MATLAB avant l'ex�cution.
	clear;
	clc;
    
    % Pr�cision
    global precision;
    global x_capteur_maximum;
    global y_capteur_maximum;
    x_capteur_maximum = 7000;
    y_capteur_maximum = 500;
    precision = 100;
	                                                                   
	% Amplitude des signaux
	global amplitude_initiale_source1;
    global amplitude_initiale_source2;
    amplitude_initiale_source1 = 1;
	amplitude_initiale_source2 = 1;
	                                                                   
	% Distance s�parant les �l�ments
    global distance_x_source1_source2;
    global distance_x_source2_capteur;
	distance_x_source1_source2 = 700;
	distance_x_source2_capteur = 5000;
	                                                                   
	% Hauteur des �l�ments
    global hauteur_sources;
    global hauteur_capteur;
	hauteur_sources = 500;
	hauteur_capteur = 300;
	                                                                   
	% Fr�quence en hertz
	frequence_signal = 150000;
	                                                                   
	% P�riode du signal
	periode_signal = 1 / frequence_signal;
	                                                                   
	% Vitesse de la lumi�re en m/s
	vitesse_lumiere = 299792458;
	                                                                   
	% Longueur d'onde
	global longueur_onde;
    longueur_onde = vitesse_lumiere / frequence_signal;
	                                                                   
	% On place la base de la source1 � l'origine (x=0).
	% On calcule ensuite les diff�rentes composantes en X.
	x_source1 = 0;
	x_source2 = x_source1 + distance_x_source1_source2;
	x_capteur = x_source2 + distance_x_source2_capteur;
	                                                                   
	% On initialise les coordonn�es initiales des sources/du capteur.
	global xy_source1;
    global xy_source2;
    global xy_capteur;
    xy_source1 = [x_source1, hauteur_sources];
    xy_source2 = [x_source2, hauteur_sources];
    xy_capteur = [x_capteur, hauteur_capteur];
	                                                                   
	% Param�tres alpha et beta
	global alpha;
    global beta;
    alpha = 0.0001;
	beta = 0;
end

%%
% *c)* �crire le code permettant de trouver la distance entre la source 1
% et le capteur ainsi que la distance entre la source 2 et le capteur.
%%

% Fonction permettant de trouver les distance entre les sources et le
% capteur.
%
% Function [distance_source1_capteur, distance_source2_capteur] = calcule_distances_sources(xy_capteur)
%
% Arguments :
%   xy_capteur : Coordonn�es du capteur
%
% Sortie :
%   distance_source1_capteur: Distance entre la source 1 et le capteur
%   distance_source2_capteur: Distance entre la source 2 et le capteur
% Partie 1 - C)
function [distance_source1_capteur, distance_source2_capteur] = calcule_distances_sources(xy_capteur)
	
	global xy_source1;
	global xy_source2;
    
    % On calcule la distance au capteur pour chacune des sources.
	distance_source1_capteur = calcule_distance(xy_source1, xy_capteur);
	distance_source2_capteur = calcule_distance(xy_source2, xy_capteur);
end

%%
% *Afficher les r�sultats*
%%
% La distance entre la source 1 et le capteur est de 5703.51.
% La distance entre la source 2 et le capteur est de 5004.

function partie1()

    global amplitude_initiale_source1;
    global amplitude_initiale_source2;
    global xy_capteur;
    
	fprintf('Partie 1\n');
    initialise_variables_globales();
    [dst1, dst2] = calcule_distances_sources(xy_capteur);
    fprintf('La distance entre la source 1 et le capteur est de %g.\n', dst1);
	fprintf('La distance entre la source 2 et le capteur est de %g.\n', dst2);
    [A1, phi1] = calcule_propagation(dst1, amplitude_initiale_source1);
    [A2, phi2] = calcule_propagation(dst2, amplitude_initiale_source2);
    amplitude = calcule_somme_signaux([A1, A2], [phi1, phi2]);
    fprintf('L''amplitude du signal re�ue par le capteur dest de %g.\n', amplitude);
end

%%
% Afin de trouver l'amplitude du signal en un point, il faut calculer 
% l'amplitude et la phase du cosinus en fonction de la distance
% parcourue par l'onde.
%
% L'att�nuation du signal dans l'air est d�finie par : 
%
% $A = A_0*e^{-\alpha l-\beta}$
%
% O�
%
% A : Amplitude du signal parcourant l m�tres
%
% A0 : L'amplitude initiale.
%
% alpha : Le coefficient d'att�nuation dans l'air.
%
% beta : Le coefficient d'absorption du sol.
%
% l : La distance en m�tres parcourue par l'onde.
%
% La phase en fonction de la distance parcourue est d�finie par : 
% 
% $Phase = (d\%ld)*\pi$
%
% o� 
%
% % : Op�ration Modulo
%
% d : La distance en m�tres
%
% ld : la longueur d'onde du signal
%
% *d)* �crire l'algorithme permettant de trouver l'amplitude et la phase du
% signal en fonction de la distance parcourue sachant qu'alpha = 0.0001 
% et beta = 0 puisque l'onde ne subit pas de r�flexion au sol. *(0.5 point)*
%%

% Fonction permettant de trouver l'amplitude et la phase
% d'un signal en fonction de son amplitude initiale et 
% de la distance qu'elle parcourt.
%
% Function [amplitude, phase] = calcule_propagation(distance, amplitude_initiale)
%
% Arguments :
%   distance: Distance parcourue par le signal
%   amplitude_initiale: L'amplitude initiale du signal (� la source)
%
% Sorties :
%   amplitude : Repr�sente l'amplitude du signal � la distance de la source
%   phase : Repr�sente la phase du signal � la distance d de la source
%%
% Partie 1 - D)
function [amplitude, phase] = calcule_propagation(distance, amplitude_initiale)

    global longueur_onde;
    global alpha;
    global beta;
    
    % On calcule l'amplitude et la phase en utilisant les fonctions
    % pr�sent�es dans le document du laboratoire.
    amplitude = amplitude_initiale * exp( -alpha * distance - beta );
    phase = ( mod(distance, longueur_onde) * 2 * pi ) / longueur_onde;
end

%%
% *e)* Connaissant l'amplitude et la phase des signaux � la position du 
% capteur, vous devez �crire une fonction permettant de trouver la 
% amplitude totale en fonction des amplitudes et des phase de chaque 
% signaux. L'amplitude total, en un point, est la somme de tous les
% signaux.
% *(0.5 point)*
%%

% Fonction permettant de trouver l'amplitude d'un signal 
% en un point, connaissant l'amplitude et la phase de 
% plusieurs signaux en ce point.
%
% function [amplitude_totale] = calcule_somme_signaux(amplitudes, phases)
%
% Arguments :
%   amplitudes : Repr�sente un vecteur des amplitudes des signaux
%   phases : Repr�sente un vecteur de la phase des signaux
%
% Sortie :
%   amplitude_totale : Repr�sente l'amplitude totale des signaux en un point
%%
% Partie 1 - E)
function [amplitude_totale] = calcule_somme_signaux(amplitudes, phases)

    % L'amplitude totale est la somme des signaux.
    somme = 0;
    for i = 1:length(amplitudes)
        phi = phases(i);
        A = amplitudes(i);
        somme = somme + A * cos(phi);
    end
    amplitude_totale = abs(somme);
end

%%
% *Afficher les r�sultats (0.5 point)*
%%

%fprintf('L'amplitude du signal re�ue par le capteur est de %g. \n', amplitude);

%% Deuxi�me partie : Optimisation de l'amplitude sans r�flexion (3.25 points)
% 
% L'objectif de cette partie est de trouver la position du capteur sur 
% l'axe des x o� l'amplitude du signal est � son maximum.
%
% *a)* �crire l'algorithme permettant de tracer l'amplitude du signal au 
% capteur en fonction de la position de celui-ci. *(0.5 point)*
%%

% Algorithme permettant de conna�tre l'amplitude du signal
% en fonction de la position en x du capteur.

% Fonction permettant de calculer l'amplitude du signal en fonction de la
% position en x du capteur.
%
% Function [amplitude_totale] = calcule_amplitude_en_fonction_capteur_x(x)
%
% Arguments :
%   x: Position en x du capteur
%
% Sorties :
%   amplitude_totale : Repr�sente l'amplitude totale du signal per�u au
%   capteur
%
% Partie 2 - A)
function [amplitude_totale] = calcule_amplitude_en_fonction_capteur_x(x)
    
    global amplitude_initiale_source1;
    global amplitude_initiale_source2;
    global hauteur_capteur;
    
    nouveau_xy_capteur = [x, hauteur_capteur];
    [dst1, dst2] = calcule_distances_sources(nouveau_xy_capteur);
    [A1, phi1] = calcule_propagation(dst1, amplitude_initiale_source1);
    [A2, phi2] = calcule_propagation(dst2, amplitude_initiale_source2);
    amplitude_totale = calcule_somme_signaux([A1, A2], [phi1, phi2]);
end

%%
% *b)* �crire la fonction qui permet d'obtenir un vecteur de distances D 
% correspondant � un vecteur de positions o� l'onde touche le sol.
%%

% Fonction permettant de g�n�rer des positions de r�flexion en x sur le
% sol.
%
% function [Xs] = genere_xs_reflexions()
%
% Sorties :
%   Xs : Des positions en Xs o� l'onde touche le sol.
%
% Partie 2 - B)
function [Xs] = genere_xs_reflexions()

    global x_capteur_maximum;
    global precision;
    
    start = 0;
    stop = x_capteur_maximum;
    Xs = linspace(start, stop, precision);
end

%%
% *Graphiques (0.25 point)*
%
% *c)* Tracer le graphique de l'amplitude en fonction de la position x du
% capteur et la d�riv�e de cette fonction dans un "subplot".
%%

% Partie 2 - C)
function [Xs, amplitudes] = trace_graphique_amplitude_en_fonction_capteur_x()

    global x_capteur_maximum;
    global precision;
    
    % On cr�er un ensemble de point sur lin�airement espac� sur
    % l'intervalle s�lectionn�e, selon la pr�cision.
    start = 0;
    stop = x_capteur_maximum;
    Xs = linspace(start, stop, precision);
    amplitudes = zeros(1, length(Xs));
    
    for i = 1:length(Xs)
        x = Xs(i);
        amplitudes(i) = calcule_amplitude_en_fonction_capteur_x(x);
    end
    
    % On cr�er une nouvelle figure pour tracer la fonction et la d�riv�e.
    figure
    subplot(2,1,1);
    plot(Xs, amplitudes);
    title('Amplitude du signal en fonction de la position du capteur');
    xlabel('Position x');
    ylabel('Amplitude');
    legend('Amplitude selon la position du capteur')
    
    % On calcule la diff�rentiation pour chaque point calcul�.
    subplot(2,1,2);
    DYs = diff(amplitudes);
    DXs = diff(Xs);
    DYDXs = zeros(1, length(DYs));
    for i = 1:length(DYs)
        DYDXs(i) = DYs(i)/DXs(i);
    end
    % On doit utiliser de nouveaux X car les valeurs sont d�caler de 1.
    Xs_diff = linspace(start, stop, length(DYs));
    
    subplot(2,1,2);
    plot(Xs_diff, DYDXs);
    title('D�riv� de la fonction amplitude en fonction de la position du capteur');
    xlabel('Position x du capteur');
    ylabel('Taux de variation');
end

%%
% *Algorithme d'optimisation (2 points)*
%
% *d)* �crire un algorithme d'optimisation que vous avez vu en classe afin de
% trouver la valeur de la position de x o� le signal est � son maximum.
%%

% Partie 2 - D)
function [amplitude, position] = optimise_position_du_capteur(Xs, amplitudes)

    % On aide un peu le d�part des it�rations en trouvant le meilleur point
    % pr�-calcul�.
    [maximum, index] = max(amplitudes);
    fun = @calcule_amplitude_en_fonction_capteur_x;
    winner = point_fixe(fun, Xs(index), 0.0001);
    fprintf('Winner: %g\n', winner);
    amplitude = maximum;
    position = Xs(index);
    
end

%%
% *Affiche les r�sultats (0.5 point)*
%%

function [racine] = point_fixe(fonction, p0, tolerance)
    maximum_iterations = 10;
    p(1) = p0;
    tolerance = 1e-05;
    
    for i = 1:maximum_iterations
        p(i+1) = fonction(p(i));
        
        if abs(p(i+1) - p(i)) < tolerance
            racine = p(i+1);
            return
        end
    end
    racine = p(i+1);
end

function partie2()

    fprintf('Partie 2\n');
    [Xs, amplitudes] = trace_graphique_amplitude_en_fonction_capteur_x();
    [amplitude, position] = optimise_position_du_capteur(Xs, amplitudes);
    fprintf('La meilleure position de x est %g donnant une amplitude de %g.\n', position, amplitude);
end

%% Troisi�me partie : Amplitude en un point avec r�flexion (2 points)
%
% Reprendre la situation de la premi�re partie, mais cette fois, il
% faut tenir compte de la r�flexion des ondes sur le sol.
%

%% 
% <<Sch�ma2.png>>
% 
% La distance dr1 est la distance s�parant la source 1 du capteur subissant
% une r�flexion sur le sol et dr2 est la distance s�parant la source 2 et 
% le capteur subissant une r�flexion sur le sol.

%%
% �crire un algorithme permettant de trouver la position o� se produira 
% la r�flexion de l'onde sur le sol.
%
% Astuce : La distance totale de la trajectoire de l'onde partant de la 
% source vers le sol et r�fl�chie vers le capteur sera minimale lorsque la
% r�flexion s'effectuera � la bonne position de x.
%
% *a)* �crire la fonction qui permet d'obtenir une matrice de distance pour 
% une onde qui touche le sol en diff�rentes positions de x. *(0.5 point)*
%%

% Fonction permettant de trouver la matrice des distances 
% en fonction du point de contact de la r�flexion d'une 
% onde sur un axe.
%
% Function [D] = distance(P1, P2, sol)
%
% Arguments :
%   P1 : Coordonn�es du point [x,z] de la source 
%   P2 : Coordonn�es du point [x,z] du capteur
%   sol : Repr�sente un vecteur contenant les positions 
%         de r�flexion sur l'axe des x
%
% Sortie :
%   D : Repr�sente la distance parcourue par l'onde pour 
%       chaque point de r�flexion du vecteur sol

% Partie 3 - A)
function [distances] = distances_trajectoires(xy_source, xy_capteur, Xs_sol)

    distances = zeros(1, length(Xs_sol));
    for i = 1:length(Xs_sol)
        xy_reflexion = [Xs_sol(i), 0];
        distances(i) = distance_trajectoire(xy_source, xy_capteur, xy_reflexion);
    end
end

function [distance] = distance_trajectoire(xy_source, xy_capteur, xy_reflexion)
    distance = calcule_distance(xy_source, xy_reflexion) + calcule_distance(xy_reflexion, xy_capteur);
end

%%
% *b)* �crire l'algorithme de recherche de la distance minimale en utilisant
% la fonction d'optimisation de la deuxi�me partie *(1 point)*
%%

function [distance, position] = optimise_trajectoires(Xs, distances)
    [minimum, index] = min(distances);
    distance = minimum;
    fun = @calcule_amplitude_en_fonction_capteur_x;
    point_fixe(fun, Xs(index), 0.0001);
    position = Xs(index);
end

function [distance] = calcule_distance_avec_reflexion(xy_source, xy_capteur)
    Xs_reflexion = genere_xs_reflexions();
    distances = distances_trajectoires(xy_source, xy_capteur, Xs_reflexion);
    [distance, position] = optimise_trajectoires(Xs_reflexion, distances);
end

%%
% *c)* Calculer l'amplitude et la phase des signaux pour les 4 distances:
%
% 1- Distance entre la source 1 et le capteur
%
% 2- Distance entre la source 1 et le capteur avec la r�flexion au sol
%
% 3- Distance entre la source 2 et le capteur
%
% 4- Distance entre la source 2 et le capteur avec la r�flexion au sol
%
% Reprendre la fonction propag de la premi�re partie. Mettre la valeur de
% beta � 0.6 pour les distances qui comportent une r�flexion sur le sol.
%%

% Partie 3 - C)
function [distances, amplitudes, phases] = calcule_amplitudes_et_phases(xy_capteur)
    
    global xy_source1;
    global xy_source2;
    global amplitude_initiale_source1;
    global amplitude_initiale_source2;
    global beta;
    
    beta = 0;
    distance1 = calcule_distance(xy_source1, xy_capteur);
    [a1, p1] = calcule_propagation(distance1, amplitude_initiale_source1);
    beta = 0.6;
    distance1r = calcule_distance_avec_reflexion(xy_source1, xy_capteur);
    [a1r, p1r] = calcule_propagation(distance1r, amplitude_initiale_source1);
    beta = 0;
    distance2 = calcule_distance(xy_source2, xy_capteur);
    [a2, p2] = calcule_propagation(distance2, amplitude_initiale_source2);
    beta = 0.6;
    distance2r= calcule_distance_avec_reflexion(xy_source2, xy_capteur);
    [a2r, p2r] = calcule_propagation(distance2r, amplitude_initiale_source2);
    
    distances = [distance1, distance1r, distance2, distance2r];
    amplitudes = [a1, a1r, a2, a2r];
    phases = [p1, p1r, p2, p2r];
    
end

%% 
% *d)* Calculer l'amplitude totale au capteur en utilisant la fonction
% somme_signaux de la premi�re partie.
%%

function [amplitude_totale] = calcule_somme_signaux_avec_reflexion(xy_capteur)

    [distances, amplitudes, phases] = calcule_amplitudes_et_phases(xy_capteur);
    amplitude_totale = calcule_somme_signaux(amplitudes, phases);
end

%%
% *Affiche les r�sultats (0.5 point)*
%%

% fprintf('L'amplitude re�ue par le capteur est de %g avec les r�flexions sur le sol. \n', amplitude_avec_reflexion);
function partie3()
    
    global xy_capteur;
    
    fprintf('Partie 3\n');
    amplitude_totale = calcule_somme_signaux_avec_reflexion(xy_capteur);
    fprintf('L''amplitude re�ue par le capteur est de %g avec les r�flexions sur le sol.\n', amplitude_totale);
    
end

%% Quatri�me partie : Optimisation de l'amplitude en un point avec r�flexion (2.75 points)
%
% L'objectif de cette partie est de trouver la position x du capteur afin
% de maximiser l'amplitude du signal re�ue par le capteur, en consid�rant 
% les 4 signaux.
%
% *a)* �crire un algorithme permettant de trouver l'amplitude en fonction 
% de la position x du capteur.
%%
%%
% *Algorithme (1 point)*
%%

% Partie 4 - A)
function [amplitude_totale] = calcule_amplitude_avec_reflexion_en_fonction_capteur_x(x)

    global hauteur_capteur;
    
    nouveau_xy_capteur = [x, hauteur_capteur];
    amplitude_totale = calcule_somme_signaux_avec_reflexion(nouveau_xy_capteur);
end

%%
% *Algorithme d'optimisation (1 point)*
%
% *b)* R�utiliser l'algorithme d'optimisation que vous avez programm�e � la
% deuxi�me partie afin de trouver la valeur de la position de x o� 
% l'amplitude signal est maximale.
%%

% Partie 4 - B)
% Voir optimise_position_du_capteur

%%
% *Graphiques (0.25 point)*
%
% *c)* Tracer le graphique de l'amplitude en fonction de la position x du
% capteur et la d�riv�e de cette fonction dans un "subplot".
%%

% Partie 4 - C)
function [Xs, amplitudes] = trace_graphique_amplitude_avec_reflexion_en_fonction_capteur_x()

    global x_capteur_maximum;
    global precision;
    
    % On cr�er un ensemble de point sur lin�airement espac� sur
    % l'intervalle s�lectionn�e, selon la pr�cision.
    start = 0;
    stop = x_capteur_maximum;
    Xs = linspace(start, stop, precision);
    amplitudes = zeros(1, length(Xs));
    
    for i = 1:length(Xs)
        x = Xs(i);
        amplitudes(i) = calcule_amplitude_avec_reflexion_en_fonction_capteur_x(x);
    end
    
    % On cr�er une nouvelle figure pour tracer la fonction et la d�riv�e.
    figure
    subplot(2,1,1);
    plot(Xs, amplitudes);
    title('Amplitude du signal avec r�flexion en fonction de la position du capteur');
    xlabel('Position x du capteur');
    ylabel('Amplitude du signal');
    
    % On calcule la diff�rentiation pour chaque point calcul�.
    subplot(2,1,2);
    DYs = diff(amplitudes);
    DXs = diff(Xs);
    DYDXs = zeros(1, length(DYs));
    for i = 1:length(DYs)
        DYDXs(i) = DYs(i)/DXs(i);
    end
    % On doit utiliser de nouveaux X car les valeurs sont d�caler de 1.
    Xs_diff = linspace(start, stop, length(DYs));
    
    subplot(2,1,2);
    plot(Xs_diff, DYDXs);
    title('D�riv� de la fonction amplitude du signal avec r�flexion en fonction de la position du capteur');
    xlabel('Position x du capteur');
    ylabel('Taux de variation');
end

%%
% *Affiche les r�sultats (0.5 points)*
%%

% fprintf('La meilleure position de x est %g pour une amplitude de %g \n', position_x, amplitude);
function partie4()
    
    fprintf('Partie 4\n');
    [Xs, amplitudes] = trace_graphique_amplitude_avec_reflexion_en_fonction_capteur_x();
    [amplitude, position] = optimise_position_du_capteur(Xs, amplitudes);
    fprintf('La meilleure position de x est %g pour une amplitude de %g\n', position, amplitude);
end

%% Cinqui�me partie : Visualisation de l'amplitude en 3D (1 point boni)
%
% Objectif de cette partie est de visualiser la distribution de
% l'amplitude si nous changeons la position du capteur dans le plan (x,z).
%
% *a)* �crire un algorithme afin de cr�er une matrice qui contient la
% distance totale des 4 signaux pour chacune des positions du capteur.
% (0 < x < 7) km et (0 < z < 0.5) km
%%

% Partie 5 - A)
function [Xs, Ys, Zs] = calcule_des_distances()

    global x_capteur_maximum;
    global y_capteur_maximum;
    global precision;
    
    Xs = linspace(0, x_capteur_maximum, precision);
    Ys = linspace(0, y_capteur_maximum, precision);
    
    for x = 1:length(Xs)
        for y = 1:length(Ys)
            xy_capteur = [Xs(x), Ys(y)];
            valeurs(x,y) = calcule_somme_signaux_avec_reflexion(xy_capteur);
        end
    end
    Zs = valeurs;
end

%%
% Visualiser la distribution de l'amplitude en fonction de la position du 
% capteur (x,z) l'aide de la fonction "surf"
%%

function partie5()
    
    fprintf('Partie 5\n');
    [Xs, Ys, Zs] = calcule_des_distances();
    figure
    surf(Xs, Ys, Zs);
end

%% Important
%
% * Les graphiques doivent tous avoir un titre, les axes doivent �tre
% identifi�s et il doit y avoir une l�gende pour chaque fonction du
% graphique.
% * Les fonctions doivent toutes avoir une description. De plus, il faut
% identifier les arguments et les variables que retourne la fonction.
% * Le code doit �tre comment�.
%
%%
% Vous devez remettre un fichier .zip contenant le fichier PDF publish et
% tous les fichiers Matlab afin de pouvoir ex�cuter le script lors de la 
% correction.

