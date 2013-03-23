class AddPriceToBuysAndSells < ActiveRecord::Migration
  def change
    add_column :buys, :price, :float
    add_column :sells, :price, :float
  end
end
