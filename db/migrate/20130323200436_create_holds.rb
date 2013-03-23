class CreateHolds < ActiveRecord::Migration
  def change
    create_table :holds do |t|
      t.integer :user_id
      t.float :value
      t.string :transaction_type
      t.integer :transaction_id

      t.timestamps
    end
  end
end
