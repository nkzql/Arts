package com.example.finalexamart.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.finalexamart.R;
import com.example.finalexamart.activity.XINanActivity;
import com.example.finalexamart.bean.Commands;
import java.util.List;
public class CardAdapter extends RecyclerView.Adapter{
    private List<Commands> mCardInfoList;
    private View view;
    private Context mContext;
    public CardAdapter(Context context){
        this.mContext=context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(mContext).inflate(R.layout.recycle_area,parent,false);
        CardHolder cardHolder=new CardHolder(view);
        return cardHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CardHolder cardHolder=(CardHolder)holder;
        Commands cardInfo=mCardInfoList.get(position);
        cardHolder.bindTo(cardInfo);
    }
    @Override
    public int getItemCount() {
        return mCardInfoList==null?0:mCardInfoList.size();
    }

    class CardHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitle;
        private ImageView mImageView;
        public CardHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.card_recycle_image);
            mTitle=itemView.findViewById(R.id.card_recycle_title);
            itemView.setOnClickListener(this);
        }
        void bindTo(Commands currentCard){
            mTitle.setText(currentCard.getDescription());
            Glide.with(mContext).load(currentCard.getBitmap()).into(mImageView);
        }
        @Override
        public void onClick(View v) {
            Commands currentCard = mCardInfoList.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, XINanActivity.class);
//            detailIntent.putExtra("title", currentCard.getTitle());
//            detailIntent.putExtra("image_resource", currentCard.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }
}
