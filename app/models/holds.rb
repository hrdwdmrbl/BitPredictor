class Holds < ActiveRecord::Base
  attr_accessible :transaction_id, :transaction_type, :user_id, :value
  belongs_to :buy, :sell, :polymorphic => true
end
