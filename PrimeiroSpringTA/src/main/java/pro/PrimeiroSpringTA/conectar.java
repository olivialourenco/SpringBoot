package pro.PrimeiroSpringTA;
import java.sql.*;

public class conectar {
  private static Connection conexao_MySql = null;
  private static String localBD = "localhost";//ip
  private static String LINK = "jdbc:mysql://" + localBD + ":3306/teste"; //porta e nome do banco
  private static final String usuario = "root";
  private static final String senha = "";  
    // Método para fazer a conexão com um banco de dados MySql
    public Connection connectionMySql() {
      try {        
        conexao_MySql = DriverManager.getConnection(LINK, usuario, senha);
//classe de conexão com metodo getConnection 
//link da conexão, usuário e senha
            System.out.println("conexão OK!");
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um "
                    + "problema na conexão com o BD", e);
        }
        return conexao_MySql;
    }
 
    public void consulta() {
        try {
            Connection con = connectionMySql();
            Statement instancia = con.createStatement();
            ResultSet rs = instancia.executeQuery("select * from aluno");
            System.out.println("Consulta ao banco:");
            while (rs.next()) {//pega dados da linha até acabar a pesquisa
              System.out.println("cod: "+rs.getInt(1));
              System.out.println("Nome: " + rs.getString(2) + 
                      " - Email: " + rs.getString(3));
              System.out.println("Código cidade: " + rs.getInt(4)+"\n");
            }            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

     public String consulta2(int cod) {
         String a = "";
         Connection con = connectionMySql();
         String sql = "select cod, nome from aluno where cod = ?";
        try {
            PreparedStatement  preparedStmt;
            preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, cod);
            ResultSet rs = preparedStmt.executeQuery();           
            while (rs.next()) {
                int code = rs.getInt("cod");
                String nome = rs.getString("nome");               
                System.out.println("nome: " + nome);    
                a = nome;
            }            
        } catch (Exception e) {
            System.out.println(e);
        }
        return a;
    }
    public boolean BuscaLogin(String login, String senha) {
        Connection con = connectionMySql();
        boolean x = false;
        String sql = "select login, senha from log where login = ? and senha = ? ";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, login);
            preparedStmt.setString(2, senha);
            ResultSet rs = preparedStmt.executeQuery();           
            while (rs.next()) {
                String login1 = rs.getString("login");
                String senha2 = rs.getString("senha");               
                System.out.println("name: " + login1);
                System.out.println("senha: " + senha2);    
                x = true;
            }
        } catch (SQLException e) {            
            e.printStackTrace();
        }
        return x;
    }
    public void Cadastro(String login, String senha) {
        Connection connection = connectionMySql();
        String sql = "insert into log (login, senha) values (?,?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);           			
            preparedStmt.setString(1, login);
            preparedStmt.setString(2, senha);          
            preparedStmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String BuscaCodnoBanco(int cod) {
        Connection con = connectionMySql();
        String x = "";
        String sql = "select nome, email, cod from aluno where cod = ?";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = con.prepareStatement(sql);
            //Efetua a troca do '?' pelos valores na query
            preparedStmt.setInt(1, cod);
            ResultSet rs = preparedStmt.executeQuery();
            //valida resultado
            while (rs.next()) {
                int code = rs.getInt("cod");
                String name = rs.getString("nome");
                String email = rs.getString("email");
                System.out.println("cod: " + code);
                System.out.println("name: " + name);
                System.out.println("email : " + email);
                x = name;   
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return x;
    }
 
    public void dataBaseInsert(String Nome, String Email, int cod) {
 
        Connection connection = connectionMySql();
        String sql = "INSERT INTO aluno (nome, email, codcidade) VALUES (?,?,?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
 
            //Efetua a troca do '?' pelos valores na query 			
            preparedStmt.setString(1, Nome);
            preparedStmt.setString(2, Email);
            preparedStmt.setInt(3, cod);
            preparedStmt.execute();
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    public void closeConnectionMySql(Connection con) {
        try {
            if (con != null) {
                con.close();
                System.out.println("Fechamento OK");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um problema para"
                    + " encerrar a conexão com o BD.", e);
        }
    }
 
}






