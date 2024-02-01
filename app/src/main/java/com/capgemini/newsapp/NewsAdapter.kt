package layout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capgemini.newsapp.News
import com.capgemini.newsapp.R

//import com.capgemini.newsapp.News


class NewsAdapter(val newsList:List<News>,val onSelection:(Int)->Unit):RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    inner class NewsHolder(inflated: View):RecyclerView.ViewHolder(inflated) {
        val img: ImageView = inflated.findViewById(R.id.imageView)
        val titleTextView:TextView=inflated.findViewById(R.id.titleT)
        val dateTextView:TextView=inflated.findViewById(R.id.dateT)
        val descriptionTextView:TextView=inflated.findViewById(R.id.descriptionT)
        val authTextView:TextView=inflated.findViewById(R.id.authorT)

        init {
            itemView.setOnClickListener{
                onSelection(adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        //inflate xml and return view holder
        val view =LayoutInflater.from(parent.context)
            .inflate(R.layout.item,parent,false)
        return NewsHolder(view)

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news=newsList[position]
        holder.authTextView.text=news.author
        holder.dateTextView.text=news.publishedAt
        holder.descriptionTextView.text=news.description
        holder.titleTextView.text=news.title

        news.urlToImage?.let{Glide.with(holder.itemView).load(it).into(holder.img)}

    }
}