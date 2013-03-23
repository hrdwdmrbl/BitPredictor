class Transaction < ActiveRecord::Base
  attr_accessible :buy_id, :prediction_id, :price, :number_of_shares, :sell_id
  belongs_to :prediction
  belongs_to :buy
  belongs_to :sell

  def execute(outcome)
    self.buy.user.subtract_amount(self.buy.number_of_shares * self.buy.price)
    if outcome == 'yes'
      #buyer is winner
      self.buy.user.add_amount(self.buy.number_of_shares * configatron.one_btc)
      self.sell.user.subtract_amount(self.sell.number_of_shares * configatron.one_btc) #seller is loser
    elsif outcome == 'no'
      # self.sell.user.subtract_amount(self.buy.number_of_shares * 0) #seller is winner
      self.sell.user.add_amount(self.sell.number_of_shares * self.sell.price) #seller is winner
    else
      throw 'Outcome Invalid'
    end
  end
end
