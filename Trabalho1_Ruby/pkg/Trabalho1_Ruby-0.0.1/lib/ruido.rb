# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require 'date'

class Ruido
  def initialize
    @ruido=0
    @data = nil
  end
  
   def get_Random_Ruido
    @ruido = rand(20..20000)
    @data=Time.now.strftime('%Y-%m-%d %H:%M:%S')
    end
    
   def get_Ruido
     return @ruido
   end
   
   def get_data
     return @data
   end
end
