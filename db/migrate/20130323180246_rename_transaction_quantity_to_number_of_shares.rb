class RenameTransactionQuantityToNumberOfShares < ActiveRecord::Migration
  def up
    rename_column :transactions, :quantity, :number_of_shares
  end

  def down
    rename_column :transactions, :number_of_shares, :quantity
  end
end
