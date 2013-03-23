class BidsController < InheritedResources::Base
    belongs_to :prediction, :optional => true
    def create
        params[:bid][:user_id] = current_user.id
        create!
    end
end
