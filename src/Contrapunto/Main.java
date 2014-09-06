/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */

package Contrapunto;

import org.jfugue.*;
import java.util.Vector;
import java.util.Scanner;

   class Node{
        private Node next, tnext;
        private Vector secvo, chvec;
        private int score=100, depth;

        public Node(){
            secvo = new Vector();
            chvec = new Vector();
            depth=0;
            next=null;
            tnext=null;
        }

        public int getNote(int n){
            return (Integer)secvo.elementAt(n);
        }

        public int getChord(int n){
            return (Integer)chvec.elementAt(n);
        }

        public int getScore(){
            return score;
        }

        public int getDepth(){
            return depth;
        }

        public Node getNext(){
            return next;
        }

        public Node getTNext(){
            return tnext;
        }

        public void setNote(int newnote){
            secvo.addElement(new Integer (newnote));
        }

        public void setChord(int newchord){
            chvec.addElement(new Integer (newchord));
        }
        
        public void setScore(int sco){
            score = sco;
        }

        public void setScore(Vector CFvec,int tos){
            int[] convarr={60, 62, 63, 65, 67, 68, 71, 72, 74, 75, 77, 79, 80, 83, 84, 86, 87, 89, 91, 92, 95, 96};
            score = this.getTNext().getScore();
            if (tos==1){
                if ((((Integer)CFvec.elementAt(depth-1)==6)||((Integer)CFvec.elementAt(depth-1)==13))&&
                    (((((Integer)CFvec.elementAt(depth)==0)||((Integer)CFvec.elementAt(depth)==7)||
                    ((Integer)CFvec.elementAt(depth)==14))&&((Integer)chvec.elementAt(depth)==5))||
                    ((((Integer)CFvec.elementAt(depth)==0)||((Integer)CFvec.elementAt(depth)==7)||
                    ((Integer)CFvec.elementAt(depth)==14))&&((Integer)chvec.elementAt(depth)==6))||
                    ((((Integer)CFvec.elementAt(depth)==4)||((Integer)CFvec.elementAt(depth)==11)||
                    ((Integer)CFvec.elementAt(depth)==18))&&((Integer)chvec.elementAt(depth)==5))))
                        score +=5;  //9 b)
                if (((Integer)CFvec.elementAt(depth)-(Integer)CFvec.elementAt(depth-1)==1)&&
                    (((Integer)chvec.elementAt(depth)==5)&&((Integer)chvec.elementAt(depth)==5))&&
                   (((Integer)CFvec.elementAt(depth)!=2)||((Integer)CFvec.elementAt(depth-1)!=2)||
                   ((Integer)CFvec.elementAt(depth)!=6)||((Integer)CFvec.elementAt(depth-1)!=6)||
                   ((Integer)CFvec.elementAt(depth)!=9)||((Integer)CFvec.elementAt(depth-1)!=9)||
                   ((Integer)CFvec.elementAt(depth)!=13)||((Integer)CFvec.elementAt(depth-1)!=13)||
                   ((Integer)CFvec.elementAt(depth)!=16)||((Integer)CFvec.elementAt(depth-1)!=16)))
                        score +=5;  //9 c)
                if (((Integer)CFvec.elementAt(depth-1)-(Integer)CFvec.elementAt(depth)==1)&&
                    (((Integer)chvec.elementAt(depth)==5)&&((Integer)chvec.elementAt(depth)==5))&&
                   ((((Integer)CFvec.elementAt(depth)!=5)&&((Integer)CFvec.elementAt(depth-1)!=4))||
                   (((Integer)CFvec.elementAt(depth)!=12)&&((Integer)CFvec.elementAt(depth-1)!=11))||
                   (((Integer)CFvec.elementAt(depth)!=19)&&((Integer)CFvec.elementAt(depth-1)!=18))))
                        score -=5;  //9 d)
                if ((((Integer)CFvec.elementAt(depth-1)-(Integer)CFvec.elementAt(depth)==2)||
                   ((Integer)CFvec.elementAt(depth)-(Integer)CFvec.elementAt(depth)==2)||
                   ((Integer)CFvec.elementAt(depth-1)-(Integer)CFvec.elementAt(depth)==5)||
                   ((Integer)CFvec.elementAt(depth)-(Integer)CFvec.elementAt(depth)==5))&&
                   ((Integer)chvec.elementAt(depth)==5)&&((Integer)chvec.elementAt(depth)==5))
                        score +=5; //9 e)
                if ((((Integer)CFvec.elementAt(depth-1)-(Integer)CFvec.elementAt(depth)==3)||
                   ((Integer)CFvec.elementAt(depth)-(Integer)CFvec.elementAt(depth)==3)||
                   ((Integer)CFvec.elementAt(depth-1)-(Integer)CFvec.elementAt(depth)==4)||
                   ((Integer)CFvec.elementAt(depth)-(Integer)CFvec.elementAt(depth)==4))&&
                   ((Integer)chvec.elementAt(depth)==5)&&((Integer)chvec.elementAt(depth)==5))
                        score +=5; //9 f)
                if ((depth==CFvec.size()-1)&&
                    ((Integer)chvec.elementAt(depth)==5))
                        score += 10;    //Final note
                if ((depth==CFvec.size()-2)&&
                    (((Integer)chvec.elementAt(depth)==5)&&((Integer)CFvec.elementAt(depth)==4))||
                    (((Integer)chvec.elementAt(depth)==5)&&((Integer)CFvec.elementAt(depth)==11))||
                    (((Integer)chvec.elementAt(depth)==5)&&((Integer)CFvec.elementAt(depth)==18))||
                    (((Integer)chvec.elementAt(depth)==6)&&((Integer)CFvec.elementAt(depth)==6))||
                    (((Integer)chvec.elementAt(depth)==6)&&((Integer)CFvec.elementAt(depth)==14)))
                        score += 10;    //A note before final note
            }
            if (tos==2){
                if ((depth>1)&&
                    ((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==2)&&
                    ((Integer)secvo.elementAt(depth-1)-(Integer)CFvec.elementAt(depth-1)==2)&&
                    ((Integer)secvo.elementAt(depth-2)-(Integer)CFvec.elementAt(depth-2)==2)&&
                    ((Integer)secvo.elementAt(depth-3)-(Integer)CFvec.elementAt(depth-3)==2))
                        score -= 10;     //4 continuous thirds
                if ((depth>1)&&
                    ((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==-2)&&
                    ((Integer)secvo.elementAt(depth-1)-(Integer)CFvec.elementAt(depth-1)==-2)&&
                    ((Integer)secvo.elementAt(depth-2)-(Integer)CFvec.elementAt(depth-2)==-2)&&
                    ((Integer)secvo.elementAt(depth-3)-(Integer)CFvec.elementAt(depth-3)==-2))
                        score -= 10;     //4 continuous thirds
                if ((depth>1)&&
                    ((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==9)&&
                    ((Integer)secvo.elementAt(depth-1)-(Integer)CFvec.elementAt(depth-1)==9)&&
                    ((Integer)secvo.elementAt(depth-2)-(Integer)CFvec.elementAt(depth-2)==9)&&
                    ((Integer)secvo.elementAt(depth-3)-(Integer)CFvec.elementAt(depth-3)==9))
                        score -= 10;     //4 continuous tenths
                if ((depth>1)&&
                    ((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==5)&&
                    ((Integer)secvo.elementAt(depth-1)-(Integer)CFvec.elementAt(depth-1)==5)&&
                    ((Integer)secvo.elementAt(depth-2)-(Integer)CFvec.elementAt(depth-2)==5)&&
                    ((Integer)secvo.elementAt(depth-3)-(Integer)CFvec.elementAt(depth-3)==5))
                        score -= 10;     //4 continuous sixths

                if ((depth>0)&&
                    ((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==4)&&
                    ((Integer)secvo.elementAt(depth-1)-(Integer)CFvec.elementAt(depth-1)==4))
                        score -= 10;    //Parallel fifths
                if ((depth>0)&&
                    ((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==-4)&&
                    ((Integer)secvo.elementAt(depth-1)-(Integer)CFvec.elementAt(depth-1)==-4))
                        score -= 10;    //Parallel fifths
                if ((depth>0)&&
                    ((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==7)&&
                    ((Integer)secvo.elementAt(depth-1)-(Integer)CFvec.elementAt(depth-1)==7))
                        score -= 10;    //Parallel octaves

                if ((depth==CFvec.size()-1)&&
                    (((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==7)||
                    ((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==0)))
                        score += 100;    //final note
    
                if (((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==7)||
                    ((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==4)||
                    ((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==0))
                        score -= 0;     //Perfect intervals

                if ((depth>0)&&((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)==1)||
                    ((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)==-1))
                        score += 2;     //Melodic step

                if ((depth>0)&&((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)>7)||
                    ((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)<-7))
                        score -= 10;     //Melodic jump greater than octave (a)
                if ((depth>0)&&((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)==6)||
                    ((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)==-6))
                        score -= 10;    //Melodic seventh jump (b)
                if ((depth>0)&&(convarr[(Integer)secvo.elementAt(depth)]-convarr[(Integer)secvo.elementAt(depth-1)]==9)||
                    (convarr[(Integer)secvo.elementAt(depth)]-convarr[(Integer)secvo.elementAt(depth-1)]==-9))
                        score -= 10;    //Melodic sixth jump (c)
                if ((depth>0)&&(convarr[(Integer)secvo.elementAt(depth)]-convarr[(Integer)secvo.elementAt(depth-1)]==6)||
                   (convarr[(Integer)secvo.elementAt(depth)]-convarr[(Integer)secvo.elementAt(depth-1)]==-6))
                        score -= 10;    //Melodic fourth augmented jump (or fifth diminished) (d)
                if ((depth>1)&&((((Integer)secvo.elementAt(depth-1)-(Integer)secvo.elementAt(depth-2)==8)&&
                   ((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)>=0))||
                   (((Integer)secvo.elementAt(depth-1)-(Integer)secvo.elementAt(depth-2)==-8)&&
                   ((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)<=0))))
                        score -= 10;    //(e)
                if ((depth>1)&&(((convarr[(Integer)secvo.elementAt(depth-1)]-convarr[(Integer)secvo.elementAt(depth-2)]==8)&&
                   ((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)>=0))||
                   ((convarr[(Integer)secvo.elementAt(depth-1)]-convarr[(Integer)secvo.elementAt(depth-2)]==-8)&&
                   ((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)<=0))))
                        score -= 10;    //(e)
            
                if ((depth>0)&&((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)==0))
                        score -= 0;     //Same note with the previous note
                if ((depth>1)&&((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)==0)&&
                    ((Integer)secvo.elementAt(depth-1)-(Integer)secvo.elementAt(depth-2)==0))
                        score -= 10;    //3 continuous same notes
                if ((depth>0)&&
                    ((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==4)&&
                    (((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)!=1)||
                    ((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)!=-1))&&
                    ((Integer)CFvec.elementAt(depth)!=0)&&((Integer)CFvec.elementAt(depth)!=7)&&
                    ((Integer)CFvec.elementAt(depth)!=3)&&((Integer)CFvec.elementAt(depth)!=10)&&
                    ((Integer)CFvec.elementAt(depth)!=4)&&((Integer)CFvec.elementAt(depth)!=11))
                        score -= 10;
                if ((depth>0)&&
                    ((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==-4)&&
                    (((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)!=1)||
                    ((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)!=-1))&&
                    ((Integer)CFvec.elementAt(depth)!=0)&&((Integer)CFvec.elementAt(depth)!=7)&&
                    ((Integer)CFvec.elementAt(depth)!=3)&&((Integer)CFvec.elementAt(depth)!=10)&&
                    ((Integer)CFvec.elementAt(depth)!=4)&&((Integer)CFvec.elementAt(depth)!=11))
                        score -= 10;
                if ((depth>0)&&
                    ((Integer)secvo.elementAt(depth)-(Integer)CFvec.elementAt(depth)==7)&&
                    (((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)!=1)||
                    ((Integer)secvo.elementAt(depth)-(Integer)secvo.elementAt(depth-1)!=-1))&&
                    ((Integer)CFvec.elementAt(depth)!=0)&&((Integer)CFvec.elementAt(depth)!=7)&&
                    ((Integer)CFvec.elementAt(depth)!=3)&&((Integer)CFvec.elementAt(depth)!=10)&&
                    ((Integer)CFvec.elementAt(depth)!=4)&&((Integer)CFvec.elementAt(depth)!=11))
                        score -= 10;
                if ((depth>0)&&
                    ((((Integer)CFvec.elementAt(depth)>(Integer)secvo.elementAt(depth-1))&&
                    ((Integer)CFvec.elementAt(depth)>(Integer)CFvec.elementAt(depth-1))&&
                    ((Integer)secvo.elementAt(depth)>(Integer)secvo.elementAt(depth-1))&&
                    ((Integer)secvo.elementAt(depth)>(Integer)CFvec.elementAt(depth-1)))||
                    (((Integer)secvo.elementAt(depth)<(Integer)CFvec.elementAt(depth-1))&&
                    ((Integer)secvo.elementAt(depth)<(Integer)secvo.elementAt(depth-1))&&
                    ((Integer)CFvec.elementAt(depth)<(Integer)CFvec.elementAt(depth-1))&&
                    ((Integer)CFvec.elementAt(depth)<(Integer)secvo.elementAt(depth-1)))))
                        score -= 10;    //
                if ((((Integer)CFvec.elementAt(depth)==6)||
                    ((Integer)CFvec.elementAt(depth)==13)||
                    ((Integer)CFvec.elementAt(depth)==20))&&
                    (((Integer)secvo.elementAt(depth)==6)||
                    ((Integer)secvo.elementAt(depth)==13)||
                    ((Integer)secvo.elementAt(depth)==20)))
                        score -=10;     //Doubling of leading note

            }
        }

        public void setNext(Node newnext){
            next = newnext;
        }

        public void setTNext(Node newnext){
            tnext = newnext;
        }

        public void setDepth(int dpth){
            depth=dpth;
        }
    }

    class NodeList{
        protected int nofElements;
        protected Node head, tail;

        public NodeList(){
            nofElements = 0;
            head = tail = null;
        }

        public boolean isEmpty(){
            return (nofElements<1);
        }

        public boolean isFirst(Node v){
            return v==head;
        }

        public Node insertFirst(Node q){
            q.setNext(head);
            head = q;
            if (++nofElements == 1)
                tail = head;
            return q;
        }

       public Node removeBest(){
            Node p,bp,pp;

            if (isEmpty())
                return null;
            bp = head;
            p = head;
            while (p!=null){
                if (p.getScore()>bp.getScore())
                    bp = p;
                p = p.getNext() ;
            }

            nofElements--;
            if (bp==head){
                head=bp.getNext();
                return bp;
            }
            pp=head;
            while (pp.getNext()!=bp)
                pp=pp.getNext();
            p = pp.getNext();
            pp.setNext(p.getNext());
            if (p==tail)
                tail = pp;
            p.setNext(null);
            return p;

        }

       public void createNode(Vector CFvec, Node tp,int n, int tos){
           Node np = new Node();
           if (tos==2)
           {
               for (int i=0;i<=tp.getDepth();i++)
                    np.setNote(tp.getNote(i));
                    np.setNote((Integer)CFvec.elementAt(tp.getDepth()+1)+n);
           }
           if (tos==1)
           {
               for (int i=0;i<=tp.getDepth();i++)
                    np.setChord(tp.getChord(i));
                    np.setChord(n);
           }
            np.setTNext(tp);
            np.setDepth(tp.getDepth()+1);
            np.setScore(CFvec,tos);
            this.insertFirst(np);
       }
    }

public class Main {

    public static void main(String[] args) {
        String v,ms;
        char tc;
        int[] notearr={7,8,9,9,9,9,9,9,8,9,10,11,10,9,8,8,8,10,9,7,6,8,7,4,7,8,9,9,8,6,7};
        int[] convarr={60, 62, 64, 65, 67, 69, 71, 72, 74, 76, 77, 79, 81, 83, 84, 86, 88, 89, 91, 93, 95, 96};
        int[] convarrm={60, 62, 63, 65, 67, 68, 71, 72, 74, 75, 77, 79, 80, 83, 84, 86, 87, 89, 91, 92, 95, 96};
        Player player = new Player();
        Pattern cantfirm = new Pattern();
        Vector CFvec = new Vector();
        Scanner input = new Scanner( System.in );
        int x;
 
        System.out.println("Type a melody (1 for c, 2 for d...) or -10 to end the melody.");
////----------T1000------------------------------\\
        while(true){
            x=input.nextInt();
            if (x==-10) break;
            CFvec.addElement(x);
        }
//\\----------T1000------------------------------//
       

////----------T2000----------------------------\\\\
//--//--------T2100--------------------------\\--\\
        NodeList chnodes = new NodeList();
        Node chn = new Node();
        
        chn.setChord(5);
        chnodes.insertFirst(chn);
       
        while (true){
            if (chnodes.isEmpty()){
                System.out.println("Couldn't create the chord array.");
                chn=null;
                break;
            }
            chn=chnodes.removeBest();
            if (chn.getDepth()==CFvec.size()-1) break;
            switch ((Integer)CFvec.elementAt(chn.getDepth())) //9 a)
            {
                case 2:case 9:case 16: case 6: case 13:
                    chnodes.createNode(CFvec, chn, 6, 1);
                    break;
                case 4:case 11:case 18:
                    chnodes.createNode(CFvec, chn, 5, 1);
                    break;
                default:
                    chnodes.createNode(CFvec, chn, 5, 1);
                    chnodes.createNode(CFvec, chn, 6, 1);
            }
            
        }
//--\\--------T2100--------------------------//--//
//--//--------T2200--------------------------\\--\\
        NodeList nodes = new NodeList();
        Node tp = new Node();
        tp.setNote((Integer)CFvec.elementAt(0)+7);
        nodes.insertFirst(tp);
        while (true){
            if (nodes.isEmpty()){
                System.out.println("Couldn't create the second voice.");
                tp=null;
                break;
            }
            tp=nodes.removeBest();
            if (tp.getDepth()==CFvec.size()-1)break;
            System.out.println(tp.getDepth());
            switch (chn.getChord(tp.getDepth()))
            {
                case 5:
                    nodes.createNode(CFvec, tp, 2, 2);
                    nodes.createNode(CFvec, tp, 4, 2);
                    nodes.createNode(CFvec, tp, 7, 2);
                    nodes.createNode(CFvec, tp, 9, 2);
                    break;
                case 6:
                    nodes.createNode(CFvec, tp, 2, 2);
                    nodes.createNode(CFvec, tp, 5, 2);
                    nodes.createNode(CFvec, tp, 7, 2);
                    nodes.createNode(CFvec, tp, 9, 2);
                    break;
            }
        }
//--\\--------T2200--------------------------//--//
//\\----------T2000----------------------------////
//------------T3000------------------------------\\
        ms = "T90 V0 I60 ";
        for (int i=0;i<CFvec.size();i++){
            ms = ms+"["+String.valueOf(convarr[(Integer)CFvec.elementAt(i)])+"] ";
            System.out.print(CFvec.elementAt(i)+" ");
        }
        ms = ms+" V1 I48 ";
        System.out.println();
        for (int i=0;i<CFvec.size();i++){
            ms = ms+"["+String.valueOf(convarr[tp.getNote(i)])+"] ";
            System.out.print(tp.getNote(i)+" ");
        }
        cantfirm.setMusicString(ms);
        player.play(cantfirm);
        System.exit(0); 

    }

}