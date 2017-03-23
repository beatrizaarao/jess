# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require "socket"
require 'rubygems'
require 'dbi'
require 'mysql'
require './database'

class Server
  def initialize( port, ip )
    @server = TCPServer.open( ip, port )
    @database = Database.new
    @response=nil
    run
    
  end

  def menu
    puts "Escolha uma das seguintes opções:"
    puts "  1->Imprimir clientes conectados"
    puts "  2->Imprimir dados de um determinado sensor"
    a= $stdin.gets.chomp.to_i(10)
    
    case a
    when 1
      coisa = @database.connected_clientes
      if coisa == 0 
        puts "Não existem clientes conectados"
      end
      menu
    when 2
      puts "Indique pf o nome de um cliente"
      cliente= $stdin.gets.chomp
      bool= @database.exists_Client(cliente)
      if bool!=0
        puts "Indique pf o tipo de sensor (\"Temperature\"/\"Noise\")"
        sensor=$stdin.gets.chomp
        @database.sensor_Values(cliente, sensor)
      else 
        puts "Cliente nao existe"
      end
      menu
    end
  end
  
  
  
  
  
  def run
    #Estabelece uma conexão com o cliente
    loop {
      Thread.start(@server.accept) do | client |
        information = client.gets.chomp.to_s
        #Separa o Nome da Localizacao
        p = information.split(" ")
        nick_name = p[0]
        location = p[1]
        
        #Verificar se cliente existe. Caso exista mudar valor para 1. Se não existir inserir.
        #A CHAVE DA BD É O NICKNAME LOGO TEM DE SER UNICO
        valor=@database.exists_Client(nick_name)
        unless valor==0
          @database.update_Client(nick_name, 1 , client)
        else
          @database.insert_Client(nick_name, location, 1, client)
        end
      
        
        puts "O cliente #{nick_name} foi conectado"
        client.puts "Connection established"
   
      #Lê as mensagens dos Clientes 
      ouveCliente(client)
      menu
      end
      
    }.join
    
  end
  
  def ouveCliente(client)
    @response = Thread.new do
    loop {  
        message = client.gets.chomp.to_s
        coisinha= message.split(" ")
         if coisinha[0].eql?("terminar")
           nome = @database.getNome(client)
           @database.update_Client(nome, 0, client)
           client.close
           
           puts "O cliente #{nome} fechou a conexão e fez #{coisinha[1]} leituras"
         else
          #Separa as mensagens por parametros
            parametros = message.split(",") 
            n = parametros[0]
            n1=n.split(': ')
            nome=n1[1]

            s = parametros[1]
            s1=s.split(': ')
            sensor=s1[1]

            v = parametros[2]
            v1=v.split(': ')
            value=v1[1]

            l = parametros[3]
            l1=l.split(': ')
            localizacao=l1[1]

            t = parametros[4]
            t1=t.split(': ')
            tempo=t1[1]
         end
          #Verifica se a mensagem diz respeito ao sensor temperarura ou ruido
          if (sensor .eql? "Temperature")
            @database.insert_Temperature(value, tempo, nome)
          else
            @database.insert_Noise(value, tempo, nome)
          end
          
      }
    end
  end

  
  
end


Server.new( 3000, "localhost" )