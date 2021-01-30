package com.mytvapp

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"
    private var param1: String? = null
    private var param2: String? = null
    
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        Handler().postDelayed({
            getAllMedia()?.let {
                setVideos(it)
            }
        }, 100)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun setVideos(list: MutableList<String?>) {
        rvVideoList.adapter = VideoItemAdapter(list, videoListener)
    }

    private val folderLister = object : OnClickFolderLister {
        override fun onClickItem(title: String) {

            if (title == "Download") {
                val list: MutableList<String?> = ArrayList()
                list.addAll(getVideosFromFolder((title))!!)
                list.addAll(getVideosFromFolder(("Downloads"))!!)
                setVideos(list)
            } else {
                getVideosFromFolder(title)?.let { setVideos(it) }

            }


        }

    }
    private val videoListener = object : OnClickVideoListener {
        override fun onClickItem(title: String) {
            val intent = Intent(activity, VideoPlayerActivity::class.java)

            intent.putExtra("uri", title)

            startActivity(intent)


        }

    }


    fun getVideosFromFolder(title: String): ArrayList<String?>? {
        val videoItemHashSet: HashSet<String> = HashSet()
        val selection = MediaStore.Video.Media.DATA + " like?"
        val selectionArgs = arrayOf("%$title%")
        val projection = arrayOf(
            MediaStore.Video.VideoColumns.DATA,
            MediaStore.Video.Media.DISPLAY_NAME
        )
        val videocursor = activity?.contentResolver?.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection, selection, selectionArgs, MediaStore.Video.Media.DATE_TAKEN + " DESC"
        )

        try {
            videocursor?.moveToFirst()
            do {
                videoItemHashSet.add(
                    videocursor?.getString(
                        videocursor.getColumnIndexOrThrow(
                            MediaStore.Video.Media.DATA
                        )
                    )!!
                )
            } while (videocursor?.moveToNext()!!)
            videocursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ArrayList(videoItemHashSet)
    }


    fun getAllMedia(): ArrayList<String?>? {
        val videoItemHashSet: HashSet<String> = HashSet()
        val projection = arrayOf(
            MediaStore.Video.VideoColumns.DATA,
            MediaStore.Video.Media.DISPLAY_NAME
        )
        val cursor: Cursor? = activity?.getContentResolver()
            ?.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null)
        try {
            cursor?.moveToFirst()
            do {
                videoItemHashSet.add(cursor?.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))!!)
            } while (cursor?.moveToNext()!!)
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ArrayList(videoItemHashSet)
    }
}