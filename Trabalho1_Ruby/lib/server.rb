# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require "socket"

class Server
  def initialize( port, ip )
    @server = TCPServer.open( ip, port )
    #{client_name:Client_TCP}
    @connections = Hash.new
    run
  end

  def run
    
    #Estabelece uma conexão com o cliente
    loop {
      Thread.start(@server.accept) do | client |
        nick_name = client.gets.chomp.to_sym
        @connections.each do |client_name, client_TCP|
          if nick_name == client_name || client == client_TCP
            client.puts "This username is already connected"
            Thread.kill self
          end
        end
        @connections[nick_name] = client
        puts "--------------------------------------------"
        connection_toString
        puts "--------------------------------------------"
        puts "O cliente #{nick_name} foi conectado"
        client.puts "Connection established"
        
      #Lê as mensagens dos Clientes 
      loop {   
        message = client.gets.chomp.to_sym
        puts "#{message}"
      #Separa as mensagens por parametros
      
        parametros = message.to_s.split(",") 
        puts"teste"
        n = parametros[0]
        n1=n.split(':')
        nome=n1[1]
        
        s = parametros[1]
        s1=s.split(':')
        sensor=s1[1]
        
        v = parametros[2]
        v1=v.split(':')
        value=v1[1]
          
        l = parametros[3]
        l1=l.split(':')
        localizacao=l1[1]
          
        t = parametros[4]
        t1=t.split(':')
        tempo=t1[1]

      }
      end
      
    }.join
    
  end
  
  def connection_toString
    @connections.each do |client_name, client_TCP|
      puts "#{client_name} => Location"
    end
  end
  
  
end


Server.new( 3000, "localhost" )