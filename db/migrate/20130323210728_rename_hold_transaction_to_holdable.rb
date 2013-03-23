class RenameHoldTransactionToHoldable < ActiveRecord::Migration
  def up
    rename_column :holds, :transaction_id, :holdable_id
    rename_column :holds, :transaction_type, :holdable_type
  end

  def down
    rename_column :holds, :holdable_id, :transaction_id
    rename_column :holds, :holdable_type, :transaction_type
  end
end
