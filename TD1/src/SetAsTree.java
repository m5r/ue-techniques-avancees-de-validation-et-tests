// This version is adapted to OpenJML and recent versions of java
/*@ nullable_by_default @*/
public class SetAsTree {
    public Integer val;
    public SetAsTree ltree;
    public SetAsTree rtree;

    //@ public invariant ((this.val != null) || (this.ltree == null && this.rtree == null));
    //@ public invariant ((this.ltree == null) || (!this.ltree.emptySet() && ( this.ltree.max() < this.val)));
    //@ public invariant ((this.rtree == null) || (!this.rtree.emptySet() && ( this.rtree.min() > this.val)));
    //@ public invariant (* no cycle in the tree *);

    // Constructor
    public SetAsTree() { // produces an empty set
        val = null;
        ltree = null;
        rtree = null;
    }

    public SetAsTree(int v) { // produces a singleton node
        val = v;
        ltree = null;
        rtree = null;
    }

    public SetAsTree(int v, SetAsTree l, SetAsTree r) { // arbitrary node
        val = v;
        ltree = l;
        rtree = r;
    }

    //getters and setters
    public /*@ pure helper @*/ Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public /*@ pure helper @*/  SetAsTree getLtree() {
        return ltree;
    }

    public void setLtree(SetAsTree ltree) {
        this.ltree = ltree;
    }

    public /*@ pure helper @*/  SetAsTree getRtree() {
        return rtree;
    }

    public void setRtree(SetAsTree rtree) {
        this.rtree = rtree;
    }

    // Application specific methods
    public void insert(int v) {
        if (this.emptySet()) {
            this.setVal(v);
            return;
        }

        if (v == this.val) {
            System.out.println("Cette valeur existe déjà dans l'arbre");
            return;
        }

        if (v > this.val) {
            if (this.rtree == null) {
                this.setRtree(new SetAsTree(v));
                return;
            }

            this.rtree.insert(v);
        }


        if (v < this.val) {
            if (this.ltree == null) {
                this.setLtree(new SetAsTree(v));
                return;
            }

            this.ltree.insert(v);
        }
    }

    public void delete(int v) {
        SetAsTree tree = this;

        while (tree.val != v) {
            if (v < tree.val) {
                tree = tree.ltree;
            }

            if (v > tree.val) {
                tree = tree.rtree;
            }
        }

        tree.val = tree.rtree.getVal();
        tree.rtree = tree.rtree.getRtree();
    }

    /*public void delete(int v) {
        SetAsTree parent = new SetAsTree();
        SetAsTree fils_gauche = this.ltree;
        SetAsTree fils_droit = this.rtree;
        parent = this;

        while (parent != null) {
            if (fils_gauche != null && fils_gauche.val < v) {
                parent = fils_gauche;
                fils_gauche = parent.ltree;
                fils_droit = parent.rtree;
                this = this.ltree;
            }

            if (fils_droit != null && fils_droit.val > v) {
                parent = fils_droit;
                fils_gauche = parent.ltree;
                fils_droit = parent.rtree;
                this = this.rtree;
            }

            if (fils_droit != null && fils_droit.val == v) {
                SetAsTree fils = new SetAsTree();
                fils = fils_droit;
                parent.rtree = fils.ltree;
                parent.rtree.rtree = fils.rtree;
                this.set = parent;
                return;

            }

            if (fils_gauche != null && fils_gauche.val == v) {
                SetAsTree fils = new SetAsTree();
                fils = fils_gauche;
                parent.ltree = fils.rtree;
                parent.ltree.ltree = fils.rtree;
                this = parent;
                return;
            }

            if (fils_gauche==null && fils_droit==null){
                return;
            }

        }
    }*/

    // Pure functions used in the specification
    public /*@ pure helper @*/ boolean emptySet() {
        return val == null;
    }

    public /*@ pure helper @*/ int min() {
        if (ltree != null && ltree.getVal() < val) {
            return ltree.min();
        } else return val;
    }

    public /*@ pure helper @*/ int max() {
        if (rtree != null && rtree.getVal() > val) {
            return rtree.max();
        } else return val;
    }

    // Non side-effecting methods
    public /*@ non_null @*/  String toString() {
        String s = "";
        if (ltree != null) {
            s = s + ltree.toString();
        }
        s = s + " " + val + " ";
        if (rtree != null) {
            s = s + rtree.toString();
        }
        return s;
    }

    public void skip() {
    } // useful to test the invariant.
}
