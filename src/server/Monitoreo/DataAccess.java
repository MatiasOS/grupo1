package server.Monitoreo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DataAccess {
	@SuppressWarnings("finally")
	
	public static Connection GetConnection()
	    {
	        Connection conexion=null;
	      
	        try
	        {
	            Class.forName("com.mysql.jdbc.Driver");
	            String servidor = "jdbc:mysql://186.39.22.147:3306/diseño"; //
	            String usuarioDB="usuario";
	            String passwordDB="usuario1";
	            conexion= DriverManager.getConnection(servidor,usuarioDB,passwordDB);
	        }
	        catch(ClassNotFoundException ex)
	        {
	            JOptionPane.showMessageDialog(null, ex, "Error1 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
	            conexion=null;
	        }
	        catch(SQLException ex)
	        {
	            JOptionPane.showMessageDialog(null, ex, "Error2 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
	            conexion=null;
	        }
	        catch(Exception ex)
	        {
	            JOptionPane.showMessageDialog(null, ex, "Error3 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
	            conexion=null;
	        }
	        finally
	        {
	        	System.out.println("conectado");
	            return conexion;
	        }
	    }

	public void insertarValor(String ip, int click) throws ClassNotFoundException
	{
		try {
			Connection conexion = DataAccess.GetConnection();
            String query = "INSERT INTO datos_no_procesados (`id`, `ip`, `fecha`, `click`) VALUES (NULL, '"+ip+"', NULL, '"+click+"');";
            System.out.println(query);
            PreparedStatement preparedStmt = (PreparedStatement) conexion.prepareStatement(query);
            preparedStmt.execute();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	    
	}
	
	public void consulta() throws ClassNotFoundException
	{
		try {
			Connection conexion = DataAccess.GetConnection();
            
           
            
            // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM datos_no_procesados";
       
            // create the java statement
            Statement st = (Statement) conexion.createStatement();
             
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
             
            // iterate through the java resultset
            while (rs.next())
            {
            	System.out.println("ip="+rs.getObject("ip")+
            		      ", click="+rs.getObject("click"));
            }
            st.close();
          }
          catch (Exception e)
          {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
          }
	
	    
	}
	
}