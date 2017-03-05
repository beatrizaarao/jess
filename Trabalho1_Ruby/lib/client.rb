# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require "socket"
class Client
  def initialize( server )
    @server = server
    @nome = nil
    @temperatura = 0
    @ruido = 0
    @localizacao = nil
    @tempo = nil
    @establish_connection = nil
    @weather_station=nil
    establish_connection
    @establish_connection.join
  end

  def establish_connection
    @establish_connection = Thread.new do
      puts "Enter the station name:"
      msg = $stdin.gets.chomp
      @server.puts( msg )
      @nome = msg.to_s
      msg = @server.gets.chomp
      puts "#{msg}"
      
      loop{
        get_Random_Ruido
        time_ruido = Time.now.getutc
        @server.puts "Name: #{@nome}, Sensor: Ruido, Value: #{@ruido}, Location: XXXXXXXX, Time: #{time_ruido}"
        sleep(30)
        get_Random_Temperatura
        time_temperatura = Time.now.getutc
        @server.puts "Name: #{@nome}, Sensor: Temperatura, Value: #{@temperatura}, Location: XXXXXXXX, Time: #{time_temperatura}" 
    }
    end
  end
 
 def get_Random_Temperatura
    @temperatura=rand(10..28)
 end
  
 def get_Random_Ruido
    @ruido = rand(20..20000)
 end
end

server = TCPSocket.open( "localhost", 3000 )
Client.new( server )