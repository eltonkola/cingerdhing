set("log.level",3)
set("log.stdout", true)
set("log.file", true)
set('log.file.path', '/tmp/radio_log.log')

%include "/var/cingerdhing/config.liq"

#telnet for remote comands, like skip reload and so on
set("server.telnet",config_telnet_enable)
set('server.telnet.port', config_telnet_port)

#error msg if nothign to play
def my_safe(s) =
  security = single("say: #{config_error_msg}")
  fallback(track_sensitive=false,[s,security])
end

#apply metadata
def apply_metadata(m) =
  title = m["title"]
  artist = m["artist"]
  print("Now playing: #{title} by #{artist}")
end

#if no metadata use description
def update_title(m) = 
  title = m["title"]
  content = config_default_meta  
  if title == "" then
    [("title",content)]
  else
    [("title","#{title} on #{content}")]
  end
end

#radio playlists
songs = playlist("/radio_media/songs")
jingles = playlist("/radio_media/jingles")
s = rotate(weights=[config_rotate_jingle,config_rotate_song], [jingles, songs])

s = on_metadata(apply_metadata,s)
s = map_metadata(update_title,s)
s = my_safe(s)

#send strem to server
output.icecast(%mp3,
		id="icecast",
        	fallible=true,
               	mount=config_mount_name, 
               	host=config_icecast_host,
               	port = config_icecast_port, 
               	password=config_icecast_password,
               	s)


