#!/bin/bash

#set -e

config_mount_name=${config_mount_name:-"source"}
config_icecast_host=${config_icecast_host:-"127.0.0.1"}
config_icecast_port=${config_icecast_port:-"8000"}
config_icecast_password=${config_icecast_password:-"hackme"}
config_error_msg=${config_error_msg:-"Error, try again later"}
config_telnet_enable=${config_telnet_enable:-false}
config_telnet_port=${config_telnet_port:-1361}
config_rotate_jingle=${config_rotate_jingle:-1}
config_rotate_song=${config_rotate_song:-3}
config_default_meta=${config_default_meta:-"Cingerdhing Streaming Radio"}

#change config
sed -ie "s/config_mount_name_value/$config_mount_name/g" /var/cingerdhing/config.liq
sed -ie "s/config_icecast_host_value/$config_icecast_host/g" /var/cingerdhing/config.liq
sed -ie "s/config_icecast_port_value/$config_icecast_port/g" /var/cingerdhing/config.liq
sed -ie "s/config_icecast_password_value/$config_icecast_password/g" /var/cingerdhing/config.liq
sed -ie "s/config_error_msg_value/$config_error_msg/g" /var/cingerdhing/config.liq
sed -ie "s/config_telnet_enable_value/$config_telnet_enable/g" /var/cingerdhing/config.liq
sed -ie "s/config_telnet_port_value/$config_telnet_port/g" /var/cingerdhing/config.liq
sed -ie "s/config_rotate_jingle_value/$config_rotate_jingle/g" /var/cingerdhing/config.liq
sed -ie "s/config_rotate_song_value/$config_rotate_song/g" /var/cingerdhing/config.liq
sed -ie "s/config_default_meta_value/$config_default_meta/g" /var/cingerdhing/config.liq


sudo su root service icecast2 start && liquidsoap /var/cingerdhing/radio.liq && sudo su root  java -jar /var/cingerdhing/cingerdhing.jar
