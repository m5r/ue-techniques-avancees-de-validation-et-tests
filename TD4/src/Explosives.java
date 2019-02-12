// Based on a B specification from Marie-Laure Potet.


// GROUPE 14

public class Explosives {
    public int nb_inc = 0;
    public String[][] incomp = new String[50][2];
    public int nb_assign = 0;
    public String[][] assign = new String[30][2];

    // Cette propriété assure que l'on n'essaie pas de stocker plus d'éléments incompatibles que possible.
    /*@ public invariant // Prop 1
      @ (0 <= nb_inc && nb_inc < 50);
      @*/

    // Cette propriété assure qu'il n'y aura pas plus de 30 éléments affectés à un batiment.
    /*@ public invariant // Prop 2
      @ (0 <= nb_assign && nb_assign < 30);
      @*/

    // Cette propriété assure que toutes les paires d'éléments incompatibles commencent par
    // la chaine de caractères "Prod".
    /*@ public invariant // Prop 3
      @ (\forall int i; 0 <= i && i < nb_inc; 
      @         incomp[i][0].startsWith("Prod") && incomp[i][1].startsWith("Prod"));
      @*/

    // Cette propriété assure que dans toutes les paires de batiment/produit,
    // l'élément à l'index 0 commence par la chaine de caractères "Bat" et
    // l'élément à l'index 1 commence par la chaine de caractères "Prod".
    /*@ public invariant // Prop 4
      @ (\forall int i; 0 <= i && i < nb_assign; 
      @         assign[i][0].startsWith("Bat") && assign[i][1].startsWith("Prod"));
      @*/

    // Cette propriété assure que l'on n'ajoute pas une incompatibilité entre un produit et lui-meme
    /*@ public invariant // Prop 5
      @ (\forall int i; 0 <= i && i < nb_inc; !(incomp[i][0]).equals(incomp[i][1]));
      @*/

    // Cette propriété assure que les incompatibilités sont correctement dupliquées pour obtenir
    // un résultat tel que pour deux produits incompatibles A et B, [A, B] et [B, A] sont présents dans le tableau
    /*@ public invariant // Prop 6
      @ (\forall int i; 0 <= i && i < nb_inc; 
      @        (\exists int j; 0 <= j && j < nb_inc; 
      @           (incomp[i][0]).equals(incomp[j][1]) 
      @              && (incomp[j][0]).equals(incomp[i][1]))); 
      @*/

    // Cette propriété assure que si deux éléments se trouvent dans le meme batiment,
    // ils ne peuvent pas etre une paire d'éléments incompatibles
    /*@ public invariant // Prop 7
      @ (\forall int i; 0 <= i &&  i < nb_assign;   // pour une paire (Bi, Pi)
      @     (\forall int j; 0 <= j && j < nb_assign;    // pour une paire (Bj, Pj)
      @        (i != j && (assign[i][0]).equals(assign [j][0])) ==> // B = Bi = Bj
      @        (\forall int k; 0 <= k && k < nb_inc; // pour une paire de produits incompatibles (Pk0, Pk1)
      @           (!(assign[i][1]).equals(incomp[k][0]))    // Pi != Pk0 OU
      @              10|| (!(assign[j][1]).equals(incomp[k][1]))))); // Pj != Pk1
      @*/

    public void add_incomp(String prod1, String prod2) {
        incomp[nb_inc][0] = prod1;
        incomp[nb_inc][1] = prod2;
        incomp[nb_inc + 1][1] = prod1;
        incomp[nb_inc + 1][0] = prod2;
        nb_inc = nb_inc + 2;
    }

    public void add_assign(String bat, String prod) {
        assign[nb_assign][0] = bat;
        assign[nb_assign][1] = prod;
        nb_assign = nb_assign + 1;
    }

    public void skip() {
    }
}
