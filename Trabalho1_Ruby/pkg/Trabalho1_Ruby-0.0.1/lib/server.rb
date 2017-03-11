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
    run
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
          @database.update_Client(nick_name, 1)
        else
          @database.insert_Client(nick_name, location, 1)
        end
      
        
        puts "O cliente #{nick_name} foi conectado"
        client.puts "Connection established"
        
      #Lê as mensagens dos Clientes 
      loop {   
        message = client.gets.chomp.to_s
        
       puts "#{message}"
        
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
      
      #Verifica se a mensagem diz respeito ao sensor temperarura ou ruido
      if (sensor .eql? "Temperature")
        @database.insert_Temperature(value, tempo, nome)
      else
        @database.insert_Noise(value, tempo, nome)
      end
      }
      end
      
    }.join
    
  end

  
  
end


Server.new( 3000, "localhost" )