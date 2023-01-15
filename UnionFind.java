/*@author Akshit Thakur
  >>implementaion of unionFind data structure using path compression
   **/
public class UnionFind {

    // the number of elements in this union find
    private int size;

    //used to track the sizes of each component
    private int[] sz;

    // id[i] points to parent of i
    // Note if id[i]==i , it means it is a root node
    private int[] id;

    // number of comoponents in the union find
    private int numComoponents;

    //constructor initialize instances
    public UnionFind(int size){
        if(size<=0){
            System.out.print("not allowed");
        }
        this.size=numComoponents=size;
        sz = new int[size];
        id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i]=i;
            sz[i]=1;
        }
    }

    // function to find which component/set 'p' belongs to

    public int find(int p){
        // find the root of component

        int root = p;
        while(root != id[root]){
            root=id[root];
        }

        // Compress the path leading to root
        // path compression
        // this reduces complexity to amortized constant time

        while(p!=root){
            int next = id[p];
            id[p] = root;
            p = next;
        }

        return root;
    }

    // Return whether or not p and q are in same set

    public boolean connected(int p , int q){
        return find(p) == find(q);
    }

    // return the size of the components

    public int componentSize(int p){
        return sz[find(p)];
    }
    // return the number of elemebts in this UnionFind/Disjoint set

    public int size(){
        return size;
    }

    // return the number of remaining components

    public int components(){
        return numComoponents;
    }

    // Unify the two components/sets containg elements 'p' and 'q';

    public void unify(int p, int q){
        int root1= find(p);
        int root2= find(q);

        // if they are allready in same group
        if(root1 == root2) return;

        //Merge two components together
        //Merge smaller into larger one

        if(sz[root1]<sz[root2]){
            sz[root2]+=sz[root1];
            id[root1] = root2;
        }
        else {
            sz[root1]+=sz[root2];
            id[root2] = root1;
        }

        // Since the roots found are different we know
        // number of components/sets has decreased by one

        numComoponents--;
    }
}
