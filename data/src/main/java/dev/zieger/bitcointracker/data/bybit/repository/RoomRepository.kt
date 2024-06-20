package dev.zieger.bitcointracker.data.bybit.repository

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.zieger.bitcointracker.domain.entities.Candle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomRepository(
    applicationContext: Context
) {

    private val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "bitcointracker_db"
    ).build()

    suspend fun insertCandles(candles: List<Candle>) =
        withContext(Dispatchers.Default) {
            db.candles().insert(*candles.map { RoomCandle(it) }.toTypedArray())
        }

    suspend fun getCandles(): List<Candle> =
        withContext(Dispatchers.Default) {
            db.candles().getAll()
        }

    suspend fun deleteCandles() =
        withContext(Dispatchers.Default) {
            db.candles().deleteAll()
        }
}

@Database(entities = [RoomCandle::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun candles(): RoomCandleDao
}

@Dao
interface RoomCandleDao {
    @Insert
    fun insert(vararg users: RoomCandle)

    @Query("SELECT * FROM RoomCandle")
    fun getAll(): List<RoomCandle>

    @Query("DELETE FROM RoomCandle")
    fun deleteAll()
}

@Entity
class RoomCandle(
    @PrimaryKey override val openTime: Long,
    override val open: Double,
    override val high: Double,
    override val low: Double,
    override val close: Double,
    override val volume: Double
) : Candle {

    constructor(candle: Candle) : this(
        candle.openTime,
        candle.open,
        candle.high,
        candle.low,
        candle.close,
        candle.volume
    )
}