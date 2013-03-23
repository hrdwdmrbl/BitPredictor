class CreatePredictions < ActiveRecord::Migration
  def change
    create_table :predictions do |t|
      t.string :name
      t.datetime :end_date
      t.text :prediction
      t.string :source
      t.integer :verified_by

      t.timestamps
    end
  end
end
