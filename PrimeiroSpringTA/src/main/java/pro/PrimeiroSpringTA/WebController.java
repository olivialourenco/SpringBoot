/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro.PrimeiroSpringTA;

/**
 *
 * @author 24167306
 */
import java.sql.Connection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller

public class WebController {
    @RequestMapping("/form")
    public String DigaOla (Model modelo){
        System.out.println("Dizendo Olá: ");
        modelo.addAttribute("mensagem", "Bem vind@");
        
        return "form";
    }    
    
    @RequestMapping(value = "/respostaform",method = RequestMethod.POST)
    public String Insere (Model modelo, String fname, String email){
    modelo.addAttribute("mensagem1", "Bem vind@!");
    System.out.println("Nome: " + fname);
    modelo.addAttribute("mensagem2", "Bem vind@!" + fname + ", email: " + email + " !");
    conectar obj = new conectar();
    Connection conexao = obj.connectionMySql();
    obj.dataBaseInsert(fname, email, 8);
    return "respostaform";
    }  
    
    @RequestMapping(value = "/bancoconecta",method = RequestMethod.POST)
    public String Banco (Model modelo1, String code){
    System.out.println("Banco conecta");
    conectar obj = new conectar();
    Connection conexao = obj.connectionMySql();
    System.out.println("Cod: " + code);
    String x = obj.consulta2(Integer.parseInt(code));
    modelo1.addAttribute("mensagem3", "Olá, " + x + ", como você está?!");
    obj.closeConnectionMySql(conexao);
    return "bancoconecta";
    }
    
    @RequestMapping("/banco")
    public String Banco2 (Model modelo){
        System.out.println("Dizendo banco: ");
        return "banco";
    }
}

