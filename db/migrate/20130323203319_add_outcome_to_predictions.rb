class AddOutcomeToPredictions < ActiveRecord::Migration
  def change
    add_column :predictions, :outcome, :string
  end
end
