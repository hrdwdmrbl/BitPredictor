class RenameBidsToSell < ActiveRecord::Migration
  def self.up
    rename_table :bids, :sells
  end

  def self.down
    rename_table :sells, :bids
  end
end
