/**
 * CPSC 219 W26 Project Demo 3
 * <p>
 * Abstract MediaItem Class
 * <p>
 * Represents a generic media item with shared attributes and behavior.
 *
 * @author Jacob Delve, Fadil Gbonjubola, Ali Jaffary, Sofia Laganas
 * @version 1.0
 */


/**
 * Abstract base for playable media item
 * Common fields and behavior that is shared by all media types
 * Subclasses must implement getDisplayInfo()
 */
public abstract class MediaItem {

    protected String title;
    protected String artist;
    protected int duration;

    /**
     * Constructs MediaItem with title, artist, and duration
     *
     * @param title the title of media item
     * @param artist the artist of the media item
     * @param duration the duration in seconds
     */
    public MediaItem(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;

    }

    /**
     * Returns the title of media item
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the artist of the media item
     *
     * @return artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Returns the duration of the media item
     *
     * @return duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns a display string specific to the media type
     * Subclasses must implement to describe a unique media type
     *
     * @return formatted display
     */
    public abstract String getDisplayInfo();

    /**
     * Returns a string of the media item using getDisplayInfo(
     *
     * @return display string
     */
    @Override
    public String toString() {
        return getDisplayInfo();
    }

}