
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.lang.Math;
import java.util.*;
import java.util.List;
import java.io.*;
import java.lang.*;

class mini extends JFrame {// Héritation du JFrame

mini(){

try {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
} catch (Exception e) {
    e.printStackTrace();
}

String qtype[]={"MCQ","Fill in the Blanks","True or False"};
String subjtype[]={"Biologie","Math","Informatique"};

// JComboBox cb=new JComboBox(qtype);
// Création du menu de choix
JComboBox cb2=new JComboBox(subjtype);

// Création des boutons
JButton b1=new JButton("Insert");
JButton b2=new JButton("Modify");
JButton b3=new JButton("Delete");
JButton b4=new JButton("Generate Quiz");

// cb.setBounds(50, 100,90,20);
// Définir le rectangle englobant de chaque composant (menu de choix et les boutons)
cb2.setBounds(30,70,150,40);
b1.setBounds(30,150,120, 40);
b2.setBounds(30,200,120, 40);
b3.setBounds(30,250,120, 40);
b4.setBounds(30,300,120, 40);

JLabel Heading;
Heading = new JLabel("", JLabel.CENTER);
add(Heading);
Heading.setSize(500,50);
Heading.setText("Choisissez un sujet et cliquez sur l'une des options ci-dessous");
add(cb2);

// Lance l'action approprié au clic du bouton 
b1.addActionListener(new ActionListener()   {
  public void actionPerformed(ActionEvent e) {
    new Insert(cb2);
  }
} );


b2.addActionListener(new ActionListener()   {
  public void actionPerformed(ActionEvent e) {
    new Modify(cb2);
  }
} );

b3.addActionListener(new ActionListener()   {
  public void actionPerformed(ActionEvent e) {
    new Delete();
  }
} );

b4.addActionListener(new ActionListener()   {
  public void actionPerformed(ActionEvent e) {
    new generate();
  }
} );

// Ajouter des boutons au cadre (frame)
add(b1);
add(b2);
add(b3);
add(b4);

setSize(600,600);
// Supprimer le Layout par défaut
setLayout(null);
// Fait apparaître le cadre sur l'écran
setVisible(true);
// Définit l'opération qui se produira par défaut lorsque l'utilisateur initiera une "fermeture" sur cette trame.
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}




public static void main(String[] args) {
new Login();
}}

class Insert extends JFrame{// Héritation du JFrame
// Creation d'un JFrame(conteneur de niveau supérieur)
JFrame f2= new JFrame("Insérer une question ");
// Creation de la liste de choix
Insert(JComboBox subjtype){
String qtype[]={" ","QCM","Remplir les espaces vides","Vrai ou faux"};
// Ajout du titre
JLabel Title;
Title = new JLabel("", JLabel.CENTER);
f2.add(Title);
Title.setSize(350,50);
Title.setText("Appuyez sur le bouton 'Ajouter' pour ajouter la question");
// Création du menu de choix
JComboBox cb=new JComboBox(qtype);
// Définir la position et la taille du ComboBox
cb.setBounds(50,100,200,30);
// Ajout du ComboBox au frame(Cadre)
f2.add(cb);
// Creation d'un champ de texte
JTextField t1 = new JTextField("Veuillez entrer la question ici");
// Définir la position et la taille du champ de texte
t1.setBounds(50,150, 200,30);
// Creation du bouton addq
JButton addq=new JButton("Ajouter une question");
// Ajout du button au frame
f2.add(addq);
// Définir la position et la taille du boutton
addq.setBounds(50,450,120, 40);


// Lance l'action approprié au clic de l'option choisie
cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                myBox(evt,cb,t1,addq,subjtype);
            }
        });
// Ajout du ComboBox au frame
f2.add(cb);
// Ajout du TextField(champ de texte) au frame
f2.add(t1);
// Fait la zone rectangulaire du frame en pixels
f2.setSize(400,600);
// Supprimer le Layout par défaut
f2.setLayout(null);
// Fait apparaître le cadre sur l'écran
f2.setVisible(true);
// Définit l'opération qui se produira par défaut lorsque l'utilisateur initiera une "fermeture" sur cette trame.
f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
// Creation des champs de texte
JTextField opA,opB,opC,opD;
JTextField ans;

void myBox(ActionEvent evt,JComboBox cb, JTextField q, JButton addq, JComboBox subjtype) {



        // Si l'option "une" du ComboBox est choisie
        if(cb.getSelectedIndex() == 1) {

          JLabel headerLabel;                ///REPEATED CODE
          headerLabel = new JLabel("", JLabel.CENTER);
          f2.add(headerLabel);
          headerLabel.setSize(350,100);
          // headerLabel.setText("Click on the correct answers");
            // Creation des champs de texte pour chaque option
            opA = new JTextField("Option A");
            opB = new JTextField("Option B");
            opC = new JTextField("Option C");
            opD = new JTextField("Option D");
            // Définir le rectangle englobant de chaque champ de texte 
            opA.setBounds(100,200, 200,30);
            opB.setBounds(100,250, 200,30);
            opC.setBounds(100,300, 200,30);
            opD.setBounds(100,350, 200,30);
            // JCheckBox checkBox1 = new JCheckBox();
            // checkBox1.setBounds(60,200, 50,50);
            // JCheckBox checkBox2 = new JCheckBox();
            // checkBox2.setBounds(60,250, 50,50);
            // f2.add(checkBox1);
            // f2.add(checkBox2);

            // Ajout des champs de texte au frame
            f2.add(opA);
            f2.add(opB);
            f2.add(opC);
            f2.add(opD);
            // Creation du champ de texte pour la reponse
            ans = new JTextField("Tapez la réponse ici");
            // Définir le rectangle englobant du champ de texte
            ans.setBounds(50,400, 200,30);
            // Ajout du champ de texte au frame
            f2.add(ans);
            // Lance l'action approprié au clic de l'option choisie
            addq.addActionListener(new ActionListener()   {
            public void actionPerformed(ActionEvent e) {
              JDBCStart b = new JDBCStart();
              b.Insert(q,opA,opB,opC,opD,subjtype,cb,ans);


        }
      });
    }
        // Si la deuxième option du ComboBox est choisie
        else if(cb.getSelectedIndex() == 2)
        {
          JLabel headerLabel;                ///REPEATED CODE
          headerLabel = new JLabel("", JLabel.CENTER);
          f2.add(headerLabel);
          headerLabel.setSize(350,100);
          headerLabel.setText("Tapez la question en utilisant un trait de soulignement ( _ ) pour l'espace vide");
          ans = new JTextField("Tapez la réponse ici");
          ans.setBounds(50,200, 200,30);
          f2.add(ans);

          addq.addActionListener(new ActionListener()   {
          public void actionPerformed(ActionEvent e) {
            JDBCStart b = new JDBCStart();

            // System.out.println(subjtype.getSelectedItem().toString());
            // System.out.println(cb.getSelectedItem().toString());
            // System.out.println(q);
            // System.out.println(ans);

            b.Insertwo(subjtype,cb,q,ans);

            }
          } );


        }
    
        // Si la troisième option du ComboBox est choisie
        else if(cb.getSelectedIndex() == 3)
        {
          JLabel headerLabel;                ///REPEATED CODE
          headerLabel = new JLabel("", JLabel.CENTER);
          f2.add(headerLabel);
          headerLabel.setSize(350,100);
          headerLabel.setText("Sélectionnez Vrai ou Faux");

          ans = new JTextField("Tapez la réponse ici");
          ans.setBounds(50,400, 200,30);
          f2.add(ans);

          addq.addActionListener(new ActionListener()   {
          public void actionPerformed(ActionEvent e) {
            JDBCStart b = new JDBCStart();
            b.Insertwo(subjtype,cb,q,ans);

        }

      });

    }
}
}

class Modify extends JFrame{
JFrame f3= new JFrame("Modifier la question");
Modify(JComboBox subjtype){

JLabel Title;
Title = new JLabel("", JLabel.CENTER);
f3.add(Title);
Title.setSize(350,50);
Title.setText("Veuillez saisir l'identifiant de la question que vous souhaitez modifier");

JTextField t1 = new JTextField("Entrez l'identifiant ici");
t1.setBounds(50,150, 200,30);

JButton modq=new JButton("Modifier la question");
f3.add(modq);
modq.setBounds(50,400,120, 40);
modq.addActionListener(new ActionListener()   {
public void actionPerformed(ActionEvent e) {
 // Give an indicator if question is deleted or not.
 JDBCStart b2 = new JDBCStart();

    b2.Modify(t1,subjtype);

  }
} );
f3.add(t1);

f3.setSize(400,500);
f3.setLayout(null);
f3.setVisible(true);
f3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
}

class Delete extends JFrame{
JFrame f4= new JFrame("Supprimer la question");
Delete(){

JLabel Title;
Title = new JLabel("", JLabel.CENTER);
f4.add(Title);
Title.setSize(350,50);
Title.setText("Veuillez saisir la question que vous souhaitez supprimer");

JTextField t1 = new JTextField("Entrez l'identifiant ici");
t1.setBounds(50,150, 200,30);

JButton delq=new JButton("Supprimer la question");
f4.add(delq);
delq.setBounds(50,400,120, 40);
delq.addActionListener(new ActionListener()   {
public void actionPerformed(ActionEvent e) {
 // Give an indicator if question is deleted or not.
 JDBCStart b = new JDBCStart();
    b.Delete(t1);

  }
} );
f4.add(t1);

f4.setSize(400,500);
f4.setLayout(null);
f4.setVisible(true);
f4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
}

class JDBCStart {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/genquiz?autoReconnect=true&useSSL=false";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "cube674";


   public static void Insertwo( JComboBox subj,JComboBox qtype,JTextField q, JTextField ans){

   Connection conn = null;
   Statement stmt = null;
   String sql = new String();
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
      stmt = conn.createStatement();
      String nothing = new String("-999");


      if(qtype.getSelectedIndex() == 3 || qtype.getSelectedIndex() == 2)
      {
        sql = "insert into bank(subject,qtype,question,answer) values('"+subj.getSelectedItem().toString()+"','"+qtype.getSelectedItem().toString()+"','"+q.getText()+"','"+ans.getText()+"')";
     }
      stmt.executeUpdate(sql);

   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
}//end main

   public static void Insert(JTextField q, JTextField a,JTextField b,
   JTextField c,JTextField d,JComboBox subj, JComboBox qtype, JTextField ans) {
   Connection conn = null;
   Statement stmt = null;
   String sql = new String();
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
      stmt = conn.createStatement();
      String nothing = new String("-999");


      if(qtype.getSelectedIndex() == 1)
      {
       sql = "insert into bank(subject,qtype,question,OptA,OptB,OptC,OptD,answer) values('"+subj.getSelectedItem().toString()+"','"+qtype.getSelectedItem().toString()+"','"+q.getText()+"','"+a.getText()+"','"+b.getText()+"','"+c.getText()+"','"+d.getText()+"','"+ans.getText()+"')";
     }
      stmt.executeUpdate(sql);

   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
}//end main

public static void Delete(JTextField id) {
Connection conn = null;
Statement stmt = null;
String sql = new String();
try{
   //STEP 2: Register JDBC driver
   Class.forName("com.mysql.jdbc.Driver");

   //STEP 3: Open a connection
   conn = DriverManager.getConnection(DB_URL, USER, PASS);

   //STEP 4: Execute a query
   stmt = conn.createStatement();


    sql = "delete from bank where id=" + Integer.parseInt(id.getText());

   stmt.executeUpdate(sql);


// }//end main

}//end Insert

catch(SQLException se){
   //Handle errors for JDBC
   se.printStackTrace();
}catch(Exception e){
   //Handle errors for Class.forName
   e.printStackTrace();
}finally{
   //finally block used to close resources
   try{
      if(stmt!=null)
         conn.close();
   }catch(SQLException se){
   }// do nothing
   try{
      if(conn!=null)
         conn.close();
   }catch(SQLException se){
      se.printStackTrace();
   }//end finally try
}//end try

}
//
public static void Modify(JTextField id, JComboBox cb2) {
Connection conn = null;
Statement stmt = null;
String sql = new String();
try{
   //STEP 2: Register JDBC driver
   Class.forName("com.mysql.jdbc.Driver");

   //STEP 3: Open a connection
   conn = DriverManager.getConnection(DB_URL, USER, PASS);

   //STEP 4: Execute a query
   stmt = conn.createStatement();


    sql = "delete from bank where id=" + Integer.parseInt(id.getText());
    JDBCStart b2 = new JDBCStart();
    String qtype = "select qtype from bank where id=" + Integer.parseInt(id.getText());
    Insert newq = new Insert(cb2);

   stmt.executeUpdate(sql);


// }//end main

}//end Insert

catch(SQLException se){
   //Handle errors for JDBC
   se.printStackTrace();
}catch(Exception e){
   //Handle errors for Class.forName
   e.printStackTrace();
}finally{
   //finally block used to close resources
   try{
      if(stmt!=null)
         conn.close();
   }catch(SQLException se){
   }// do nothing
   try{
      if(conn!=null)
         conn.close();
   }catch(SQLException se){
      se.printStackTrace();
   }//end finally try
}//end try

}
}



class Login extends JFrame{
JFrame f3 = new JFrame("Page d'authentification");
Login(){

f3.setSize(400,400);
JLabel Title;
Title = new JLabel("", JLabel.CENTER);
f3.add(Title);
Title.setSize(50,50);
Title.setText("Veuillez saisir vos identifiants ");

JTextField t1 = new JTextField("Nom d'utilisateur");
JTextField t2 = new JTextField("Mot de passe");

t1.setBounds(100,200,200,30);
t2.setBounds(100,250,200,30);
f3.setVisible(true);

JButton lgn=new JButton("Connexion");

f3.setLayout(null);

lgn.setBounds(100,300,200,30);
f3.add(t1);
f3.add(t2);
f3.add(lgn);
lgn.addActionListener(new ActionListener()   {
public void actionPerformed(ActionEvent e) {
 // Give an indicator if question is deleted or not.
 // init mini

 if(t1.getText().equalsIgnoreCase("admin") && t2.getText().equalsIgnoreCase("admin"))
 new mini();

  }
} );
}
}

class Operation
{
    MysqlCon con=new MysqlCon();
    Connection conn= (new Connect()).getConnection();
    ResultSet getRow(int id) throws SQLException
    {
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(con.getRow(id));
        return rs;
    }



    void generateQuestions(int n,boolean subs[]) throws SQLException
    {
        Statement stmt=conn.createStatement();
        String query=new String();
        if(subs[0]&&subs[1]&&subs[2])
            query=con.generateTest(n);
        else if(subs[0]&&subs[1]&&!subs[2])
            query=con.generateTest(n,"Biologie","Maths");
        else if(subs[0]&&!subs[1]&&subs[2])
            query=con.generateTest(n,"Biologie","Informatique");
        else if(!subs[0]&&subs[1]&&subs[2])
            query=con.generateTest(n,"Maths","Informatique");
        else if(subs[0]&&!subs[1]&&!subs[2])
            query=con.generateTest(n,"Biologie");
        else if(!subs[0]&&subs[1]&&!subs[2])
            query=con.generateTest(n,"Maths");
        else if(!subs[0]&&!subs[1]&&subs[2])
            query=con.generateTest(n,"Informatique");
        else{}//Write something here}
        ResultSet rs=stmt.executeQuery(query);
        try(PrintWriter writer= new PrintWriter(new FileOutputStream("questions.txt", false));){
        while(rs.next())
        {
            System.out.println(rs.getInt("id")+" "+rs.getString("subject")+" "+rs.getString("question"));
            writer.println(rs.getInt("id")+"\t"+rs.getString("subject")+"\t"+rs.getString("question"));
        }
        stmt.close();
        Desktop.getDesktop().open(new File("questions.txt"));
    }
    catch(Exception e){
    System.out.println(e);
    }
    }
    void generateSolutions(int n,boolean subs[]) throws SQLException
    {
        Statement stmt=conn.createStatement();
        String query=new String();
        if(subs[0]&&subs[1]&&subs[2])
            query=con.generateTest(n);
        else if(subs[0]&&subs[1]&&!subs[2])
            query=con.generateTest(n,"Biologie","Chemistry");
        else if(subs[0]&&!subs[1]&&subs[2])
            query=con.generateTest(n,"Biologie","Maths");
        else if(!subs[0]&&subs[1]&&subs[2])
            query=con.generateTest(n,"Chemistry","Maths");
        else if(subs[0]&&!subs[1]&&!subs[2])
            query=con.generateTest(n,"Biologie");
        else if(!subs[0]&&subs[1]&&!subs[2])
            query=con.generateTest(n,"Chemistry");
        else if(!subs[0]&&!subs[1]&&subs[2])
            query=con.generateTest(n,"Maths");
        else{}
        ResultSet rs=stmt.executeQuery(query);
        try(PrintWriter writer = new PrintWriter(new FileOutputStream("answers.txt", false));)
        {
        while(rs.next())
        {
            String corr=rs.getString("answer");
            int id=rs.getInt("id");
            // Statement stmt2=conn.createStatement();
            // ResultSet rs2=stmt2.executeQuery(con.getAnswer(id,corr));
            // while(rs2.next())
                // {
                                System.out.println(id+" "+rs.getString("subject")+" "+(corr));
                                writer.println(id+"\t"+rs.getString("subject")+"\t"+(corr));
            //     }
            // rs2.close();
            }
            Desktop.getDesktop().open(new File("answers.txt"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        rs.close();
        stmt.close();
        }
    }


class MysqlCon
{

    String generateTest(int n)
    {
        String sqlGenerate=String.format("select * from bank order by rand() limit %d;",n);
        return sqlGenerate;
    }
    String generateTest(int n,String sub1)
    {
        System.out.println(sub1);

        String sqlGenerate=String.format("select * from bank where subject = '%s' order by rand() limit %d;",sub1,n);
        return sqlGenerate;
    }
    String generateTest(int n,String sub1,String sub2)
    {
        String sqlGenerate=String.format("select * from bank where subject = '%s' or subject = '%s' order by rand() limit %d;",sub1,sub2,n);
        return sqlGenerate;
    }
    String getAnswer(int id, String corr)
    {
        System.out.println(corr);
        String sqlAnswer=String.format("select %s from bank where id=%d;",corr,id);
        return sqlAnswer;
    }
    String getRow(int id)
    {
        String sqlRow=String.format("select * from bank where id=%d",id);
        return sqlRow;
    }
}

class Connect{

private Connection conn;
  public Connection getConnection()
      {

          try
          {
              if(conn==null)
              {
                  conn=DriverManager.getConnection(
                 "jdbc:mysql://localhost:3306/genquiz?useSSL=false","root","cube674");
              }
          }
          catch(SQLException e)
          {
              e.printStackTrace();
          }
          return conn;
      }

}

class generate extends Frame implements WindowListener, MouseListener
{
    private Checkbox s1;
    private Checkbox s2;
    private Checkbox s3;
    private Label nL;
    private TextField n;
    private Label lbl;
    private Button bn;
    private Choice c;
    public generate()
    {
        setLayout(new FlowLayout());
        lbl= new Label("Sélectionnez vos sujets:");
        add(lbl);
        s1=new Checkbox("Biologie");
        add(s1);
        s2=new Checkbox("Maths");
        add(s2);
        s3=new Checkbox("Informatique");
        add(s3);
        n=new TextField(10);
        nL=new Label("Entrez le nombre de requêtes");
        add(nL);
        add(n);
        bn= new Button("Générer");
        bn.addMouseListener(this);
        add(bn);
        c=new Choice();
        c.add("Questions");
        c.add("Solutions");
        add(c);
        addWindowListener(this);
        setSize(250,200);
        setVisible(true);
    }
    public void mousePressed(MouseEvent evt)
    {
        Operation q=new Operation();
        String ch=c.getItem(c.getSelectedIndex());
        int num=Integer.parseInt(n.getText());
        boolean subs[]={s1.getState(),s2.getState(),s3.getState()} ;
        try{
            if(ch.equalsIgnoreCase("Questions"))
                q.generateQuestions(num,subs);
            if(ch.equalsIgnoreCase("Solutions"))
                q.generateSolutions(num,subs);
                }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
    }
    public void mouseClicked(MouseEvent evt){}
    public void mouseEntered(MouseEvent evt){}
    public void mouseExited(MouseEvent evt){}
    public void mouseReleased(MouseEvent evt){}
    public void windowClosing(WindowEvent evt)
    {
        dispose();
    }
    public void windowOpened(WindowEvent evt) { }
    public void windowClosed(WindowEvent evt) { }
    public void windowIconified(WindowEvent evt) { }
    public void windowDeiconified(WindowEvent evt) { }
    public void windowActivated(WindowEvent evt) { }
    public void windowDeactivated(WindowEvent evt) { }
}
