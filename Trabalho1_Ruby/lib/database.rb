# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require 'rubygems'
require 'dbi'
require 'mysql'

class Database
  def initialize
    # connect to the MySQL server
     @dbh = DBI.connect('DBI:Mysql:Ruby_db:localhost', 'root', 'mysql')
     rescue DBI::DatabaseError => e
        puts "An error occurred"
        puts "Error code:    #{e.err}"
      
  end
 
  def insert_Client (nome, localizacao, valor)
    @dbh.do("INSERT INTO client (Name, Location, Connected) VALUES (?,?,?);", nome, localizacao , valor)
    rescue DBI::DatabaseError => e
        puts "An error occurred in insert_Client "
        puts "Error code:    #{e.err}"
  end
  
  def update_Client (nome, valor)
    @dbh.do("UPDATE client SET Connected=? WHERE Name=?;", valor, nome) 
    rescue DBI::DatabaseError => e
        puts "An error occurred in update_Client"
        puts "Error code:    #{e.err}"
  end
  
  #Verifica se o client em questao ja existe na BD
  def exists_Client (nome)
    count= @dbh.select_one("SELECT count(*) FROM client WHERE Name=?;",nome)
    return count[0]
    rescue DBI::DatabaseError => e
        puts "An error occurred in exists_Client"
        puts "Error code:    #{e.err}"
  end
  
  def insert_Temperature (valor,data,nome)
    @dbh.do("INSERT INTO Temperature (Value, Date, Client_name) VALUES (?,?,?);", valor, data , nome)
    rescue DBI::DatabaseError => e
        puts "An error occurred in insert_Temp"
        puts "Error code:    #{e.err}"
  end
  
  def insert_Noise (valor,data,nome)
    @dbh.do("INSERT INTO Noise (Value, Date, Client_id) VALUES (?,?,?);", valor, data , nome)
    
    rescue DBI::DatabaseError => e
        puts "An error occurred in insert_noise"
        puts "Error code:    #{e.err}"
  end
  
  #Listar os clientes que estão ‘ligados’ e a sua respetiva localização
  def connected_clientes
    sth = @dbh.execute("SELECT * FROM client where Connected=1")
    while row = sth.fetch do
     puts "Name: #{row[0]}, Location: #{row[1]}"
    end
    sth.finish
  end
  
  #Listar os valores lidos de um determinado sensor, sendo fornecido com
  #parâmetro um identificador único do cliente.
  #O sensor_type é Temperature ou Noise. Estou a assumir que vais buscar isto como uma string ao que o cliente escolhe no menu
  def sensor_Values(client_name, sensor_type)
    if sensor_type .eql? "Temperature"
      sth = @dbh.execute("SELECT  Temperature.Value, Temperature.Date FROM Temperature join client on Temperature.client_name=?",client_name)
    else
      sth = @dbh.execute("SELECT  Noise.Value, Noise.Date FROM Noise join client on Noise.client_id=?",client_name)
    end
    while row = sth.fetch do
     puts "Value: #{row[0]}, Date: #{row[1]}"
    end
  end
  
end
