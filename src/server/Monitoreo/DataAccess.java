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
	            String servidor = "jdbc:mysql://186.39.18.252:3306/dise�o"; //
	            String usuarioDB="usuario";
	            String passwordDB="usuario1";
	            conexion= DriverManager.getConnection(servidor,usuarioDB,passwordDB);
	        }
	        catch(ClassNotFoundException ex)
	        {
	            JOptionPane.showMessageDialog(null, ex, "Error1 en la Conexi�n con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
	            conexion=null;
	        }
	        catch(SQLException ex)
	        {
	            JOptionPane.showMessageDialog(null, ex, "Error2 en la Conexi�n con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
	            conexion=null;
	        }
	        catch(Exception ex)
	        {
	            JOptionPane.showMessageDialog(null, ex, "Error3 en la Conexi�n con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
	            conexion=null;
	        }
	        finally
	        {
	            return conexion;
	        }
	    }

	public void insertarValor(Click c) throws ClassNotFoundException
	{
		try {
			Connection conexion = DataAccess.GetConnection();
			String query = "			INSERT INTO `dise�o`.`datos_no_procesados` (`id`, `ip`, `fecha`, `link`, `browser`, `so`, `idcliente`, `hora`) VALUES (NULL, '"+c.getIp()+"', '"+c.getFecha()+"', '"+c.getLink()+"', '"+c.getBrowser()+"', '"+c.getSo()+"', '"+c.getId()+"','"+c.getHora()+"');";
            PreparedStatement preparedStmt = (PreparedStatement) conexion.prepareStatement(query);
            preparedStmt.execute();
			conexion.close();
			 System.out.println("Se guard� el click en la Base de Datos");
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