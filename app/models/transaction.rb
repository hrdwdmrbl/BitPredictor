class Transaction < ActiveRecord::Base
  attr_accessible :buyer_id, :prediction_id, :price, :quantity, :seller_id
  belongs_to :prediction
  belongs_to :buyer, :class_name => "User", :foreign_key => "buyer_id"
  belongs_to :seller, :class_name => "User", :foreign_key => "seller_id"
end
