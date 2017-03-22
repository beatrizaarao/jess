# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require "socket"
require './temperatura'
require './ruido'

class Client
  def initialize( server )
    @server = server
    @nome = nil
    @ruido = Ruido.new
    @temperatura = Temperatura.new
    @localizacao = nil
    @threadRuido = nil
    @threadTemp = nil
    @threadClient = nil
    establish_connection
    @threadTemp.join
    @threadRuido.join
    @threadClient.join     
  end

   def listenRuido
    @threadRuido = Thread.new do
      loop {
        @ruido.get_Random_Ruido
        time_ruido = @ruido.get_data
        @server.puts "Name: #{@nome}, Sensor: Noise, Value: #{@ruido.get_Ruido}, Location: #{@localizacao}, Time: #{time_ruido}"
        sleep(1)
      }
    end
  end
  
  def listenClient
    @threadClient = Thread.new do
      loop {
        mensagem = $stdin.gets.chomp.to_s
        if mensagem.eql?("terminar")
          @server.puts "#{mensagem}"
          @server.close
          @threadTemp.terminate
          @threadRuido.terminate
          @threadClient.terminate
        end
        
      }
    end
  end
  
  def listenTemp
    @threadTemp = Thread.new do
      loop {
        sleep(30)
        @temperatura.get_Random_Temperatura
        time_temperatura = @temperatura.get_data
        @server.puts "Name: #{@nome}, Sensor: Temperature, Value: #{@temperatura.get_Temperatura}, Location: #{@localizacao}, Time: #{time_temperatura}" 
      }
    end
  end
  
  
  
  def establish_connection
    puts "Enter the station name:"
    msg = $stdin.gets.chomp
    random_Location
    puts "#{@localizacao}"
    @server.puts( msg + " " + @localizacao)
    @nome = msg.to_s
    msg = @server.gets.chomp
    puts "#{msg}"
    listenRuido
    listenTemp
    listenClient
  end

  def random_Location
    @localizacao="Latitude->#{40.50 + rand(41.50-41.50)}|Longitude->#{-(-9.0) + rand((-8.0)-(-9.0))}"
  end
end

server = TCPSocket.open( "localhost", 3000 )
Client.new( server )