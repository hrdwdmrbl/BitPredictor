class User < ActiveRecord::Base
  devise :database_authenticatable, :registerable, :recoverable, :rememberable, :trackable, :validatable
  devise :omniauthable, :omniauth_providers => [:facebook]

  has_many :predictions
  has_many :sells
  has_many :buys

  # Setup accessible (or protected) attributes for your model
  attr_accessible :email, :password, :password_confirmation, :remember_me, :provider, :uid, :name
  # attr_accessible :title, :body

  before_create :generate_bitcoin_account

  def self.find_for_facebook_oauth(auth, signed_in_resource=nil)
    user = User.where(:provider => auth.provider, :uid => auth.uid).first
    unless user
      user = User.create(name:auth.extra.raw_info.name,
                           provider:auth.provider,
                           uid:auth.uid,
                           email:auth.info.email,
                           password:Devise.friendly_token[0,20]
                           )
    end
    user
  end

  def generate_bitcoin_account
    self.public_key = Guid.new.to_s
    self.private_key = Guid.new.to_s
  end

  def bitcoin_available
    self.account_balance - self.holds
  end

  def account_balance
    configatron.one_btc*2
  end

  def holds
    self.outstanding_buys + self.outstanding_sells
  end

  def outstanding_sells
    self.sells.incomplete.map{|sell| sell.price * configatron.one_btc}.sum
  end
  def outstanding_buys
    self.buys.incomplete.map{|buy| buy.price * buy.number_of_shares}.sum
  end

  def transfer

  end

  def add_amount(number_of_bitcoin)
  end

  def subtract_amount(number_of_bitcoin)
  end
end
