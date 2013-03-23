class AddTransactionIdToSellsAndBuys < ActiveRecord::Migration
  def change
    add_column :buys, :transaction_id, :integer
    add_column :sells, :transaction_id, :integer
  end
end
