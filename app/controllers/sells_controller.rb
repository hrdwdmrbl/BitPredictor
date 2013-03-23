class SellsController < InheritedResources::Base
    belongs_to :prediction, :optional => true
    def create
        params[:sell][:user_id] = current_user.id
        create!
    end
end
