class Hold < ActiveRecord::Base
  attr_accessible :transaction_id, :transaction_type, :user_id, :value
  belongs_to :holdable, :polymorphic => true
end
