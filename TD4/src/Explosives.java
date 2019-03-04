// Based on a B specification from Marie-Laure Potet.

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

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

    // Cette propriété assure qu'il n'y aura pas plus de 30 éléments affectés aux batiments.
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
      @              || (!(assign[j][1]).equals(incomp[k][1]))))); // Pj != Pk1
      @*/

    /*@ public invariant // Prop 8
      @ (\forall int i; i > 0 && i < nb_assign;
      @     (\forall int j; j > 0 && j < nb_assign;
      @         (
      @             i != j && (assign[i][0]).equals(assign [j][0])) ==>
      @             (!assign[i][1].equals(assign[j][1])
      @        )
      @     )
      @ );
      @*/

    /*@ public invariant // Prop 9
	  @ (\forall int i; i >= 0 &&  i < nb_assign;
      @     (\forall int j; j >= i && j < nb_assign;
      @        (i != j && (assign[i][1]).equals(assign[j][1])) ==>
      @		   (\forall int k; k >= j && k < nb_assign;
      @		      (k != j && (assign[k][1]).equals(assign[j][1])) ==>
      @		      !\exists int l; l >= 0 && l < nb_assign && assign[l][1].equals(assign[i][1]))));
      @*/

    /*@ public constraint // Prop 10
	  @ (\old(nb_inc) <= nb_inc);
	  @*/

    //@ requires prod1.startsWith("Prod") && prod2.startsWith("Prod") && !prod1.equals(prod2) && nb_inc < 48;
    public void add_incomp(String prod1, String prod2) {
        incomp[nb_inc][0] = prod1;
        incomp[nb_inc][1] = prod2;
        incomp[nb_inc + 1][1] = prod1;
        incomp[nb_inc + 1][0] = prod2;
        nb_inc = nb_inc + 2;
    }

    //@ requires bat.startsWith("Bat") && prod.startsWith("Prod") && nb_assign < 29;
    /*@ requires
      @ (\forall int i; i >= 0 && i < nb_assign;
      @     assign[i][0] == bat && (\forall int j; j >= 0 && j < nb_inc;
      @         !incomp[j][0].equals(prod) && !incomp[j][1].equals(assign[i][1])
      @ ));
      @*/
    public void add_assign(String bat, String prod) {
        assign[nb_assign][0] = bat;
        assign[nb_assign][1] = prod;
        nb_assign = nb_assign + 1;
    }

    public void skip() {
    }

    //@ ensures \result.startsWith("Bat");
    public String findBat(String prod) {
        ArrayList<String> incompatibilities = new ArrayList<>();
        HashMap<String, ArrayList<String>> batiments = new HashMap<>();

        for (int i = 0; i < nb_inc; i++) {
            if (incomp[i][0] == prod) {
                incompatibilities.add(incomp[i][1]);
            }
        }

        for (int j = 0; j < nb_assign; j++) {
            String batimentName = assign[j][0];
            String productName = assign[j][1];
            ArrayList<String> products = batiments.containsKey(batimentName) ? batiments.get(batimentName) : new ArrayList<>();
            products.add(productName);
            batiments.put(batimentName, products);
        }

        for (Map.Entry<String, ArrayList<String>> entry : batiments.entrySet()) {
            String potential_batiment = entry.getKey();
            ArrayList<String> products = entry.getValue();

            ArrayList<String> filteredProducts = new ArrayList<>();
            for (int i = 0; i < products.size(); i++) {
                String product = products.get(i);
                if (incompatibilities.contains(product)) {
                    filteredProducts.add(product);
                }
            }

            if (filteredProducts.size() > 0 && !filteredProducts.contains(prod)) {
                return potential_batiment;
            }
        }

        return "Bat_rien";
    }
}
