class RenamePriceToNumberOfShares < ActiveRecord::Migration
  def up
    rename_column :sells, :price, :number_of_shares
    rename_column :buys, :price, :number_of_shares
  end

  def down
    rename_column :sells, :price, :number_of_shares
    rename_column :buys, :price, :number_of_shares
  end
end
