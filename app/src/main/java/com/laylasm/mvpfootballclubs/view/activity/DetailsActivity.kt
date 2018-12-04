package com.laylasm.mvpfootballclubs.view.activity

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.model.*
import com.laylasm.mvpfootballclubs.presenter.DetailsPresenter
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat

class DetailsActivity : AppCompatActivity(), DetailsView.View {

    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    lateinit var event: Data
    lateinit var mPresenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val service = FootballMatchApi.getClient().create(FootballMatchEndPoint::class.java)
        val request = TeamMatchRepoImp(service)
        val localRepo = LocalRepoImp(applicationContext)
        mPresenter = DetailsPresenter(this, request, localRepo)

        event = intent.getParcelableExtra("match")
        mPresenter.getTeamsBadgeAway(event.idAwayTeam)
        mPresenter.getTeamsBadgeHome(event.idHomeTeam)
        mPresenter.checkMatch(event.idEvent)
        initData(event)
        supportActionBar?.title = event.strEvent
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.team_menu_fav -> {
                if (!isFavorite) {
                    mPresenter.insertMatch(
                            event.idEvent, event.idHomeTeam, event.idAwayTeam)
                    toast("Yeah berhasil disave ke Favorit!")
                    isFavorite = !isFavorite
                } else {
                    mPresenter.deleteMatch(event.idEvent)
                    toast("Berhasil Terhapus coy!")
                    isFavorite = !isFavorite
                }
                setFavorite()
                true
            }
            R.id.menu_main_notif -> {
                if (CalendarHelper.haveCalendarReadWritePermissions(this@DetailsActivity)) {
                    onEventReminder();

                } else {
                    CalendarHelper.requestCalendarReadWritePermission(this@DetailsActivity);
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun initData(data: Data) {
        if (event.intHomeScore == null) {
            tvDateMatch.setTextColor(applicationContext.getColor(R.color.colorPrimary))
        }
        tvDateMatch.text = event.dateEvent?.let { DateHelper.formatDateToMatch(it) }
        tvHomeName.text = data.strHomeTeam
        tvHomeScore.text = data.intHomeScore
        tvAwayName.text = data.strAwayTeam
        tvAwayScore.text = data.intAwayScore
        tvHomeGoalDetails.text = data.strHomeGoalDetails
        tvAwayGoalDetails.text = data.strAwayGoalDetails
        tvHomeGK.text = data.strHomeLineupGoalkeeper
        tvAwayGK.text = data.strAwayLineupGoalkeeper
        tvHomeDefense.text = data.strHomeLineupDefense
        tvAwayDefense.text = data.strAwayLineupDefense
        tvHomeMiddle.text = data.strHomeLineupMidfield
        tvAwayMiddle.text = data.strAwayLineupMidfield
        tvHomeForward.text = data.strHomeLineupForward
        tvAwayForward.text = data.strAwayLineupForward
        tvHomeSub.text = data.strHomeLineupSubstitutes
        tvAwaySub.text = data.strAwayLineupSubstitutes
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_fav_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_fav_24dp)
    }
    override fun setFavoriteState(favList: List<FavoriteDB>) {
        if (!favList.isEmpty()) isFavorite = true
    }

    private fun getGoogleCalendarId(): Long {
        val projection = arrayOf(CalendarContract.Calendars._ID, CalendarContract.Calendars.NAME,
                CalendarContract.Calendars.ACCOUNT_NAME, CalendarContract.Calendars.ACCOUNT_TYPE)
        if (CalendarHelper.haveCalendarReadWritePermissions(this@DetailsActivity)) {
            val calCursor = this.contentResolver
                    .query(CalendarContract.Calendars.CONTENT_URI, projection,
                            CalendarContract.Calendars.VISIBLE + " = 1", null, CalendarContract.Calendars._ID + " ASC")

            if (calCursor!!.moveToFirst()) {
                do {
                    val id = calCursor.getLong(0)
                    return id

                } while (calCursor.moveToNext())
            }
        }
        return -1

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == CalendarHelper.CALENDARHELPER_PERMISSION_REQUEST_CODE) {
            if (CalendarHelper.haveCalendarReadWritePermissions(this)) {
                Toast.makeText(this, "Have Calendar Read/Write Permission.",
                        Toast.LENGTH_LONG).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun onEventReminder() {
        if (event.intHomeScore != null) {
            toast("Yah sayang sekali event ini telah berlalu, silahkan move on ke event yang lain yaa :)")
        } else {
            val calId = getGoogleCalendarId()
            if (calId == -1L) {
                Toast.makeText(this, "Ada yang salah nih, coba lagi ya :(",
                        Toast.LENGTH_SHORT).show()
                return
            }
            val title = event.strEvent
            val clock = event.strTime.split("+")[0]
            val dt = event.dateEvent
            val dateWithClock = "$dt $clock"
            val simpleDate = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")
            val date = simpleDate.parse(dateWithClock)
            val timeInMillis = date.time
            val end = timeInMillis + 5400000
            val intent = Intent(Intent.ACTION_EDIT)
            intent.type = "vnd.android.cursor.item/event"
            intent.putExtra(CalendarContract.Events.TITLE, title)
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, timeInMillis)
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "on Youtube")
            startActivity(intent)
        }
    }

    override fun viewTeamBadgeAway(team: TeamData) {
        Glide.with(applicationContext)
                .load(team.strTeamBadge)
                .apply(RequestOptions().placeholder(R.drawable.ic_add_fav_24dp))
                .into(ivAway)
    }

    override fun viewTeamBadgeHome(team: TeamData) {
        Glide.with(applicationContext)
                .load(team.strTeamBadge)
                .apply(RequestOptions().placeholder(R.drawable.ic_add_fav_24dp))
                .into(ivHome)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }


}