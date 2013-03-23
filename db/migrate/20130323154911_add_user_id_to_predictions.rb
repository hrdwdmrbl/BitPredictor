class AddUserIdToPredictions < ActiveRecord::Migration
  def change
    add_column :predictions, :user_id, :integer
  end
end
