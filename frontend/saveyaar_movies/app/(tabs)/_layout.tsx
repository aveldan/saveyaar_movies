import { Tabs } from 'expo-router';
import React from 'react';
import { HomeIcon, SaveIcon, SearchIcon, MySubscriptionsIcon } from '@/assets/svg';
import { StyleSheet, TouchableOpacity, TouchableOpacityProps } from 'react-native';
import { BlurView } from 'expo-blur';

export const TAB_SCREENS = [
    {
        name: "index",
        title: "Home",
        icon: ({ color, focused }: { color: string; focused: boolean }) => (
          <HomeIcon width={28} height={28} isActive={focused}/>
        ),
        style: {
          backgroundColor: 'blue',
        }
    },
    {
        name: "(discover)/discover",
        title: "Discover",
        icon: ({ color, focused }: { color: string; focused: boolean }) => (
          <SearchIcon width={28} height={28} isActive={focused}/>
        ),
        style: {
          backgroundColor: 'red',
        }
    },
    {
        name: "(saved)/saved",
        title: "Saved",
        icon: ({ color, focused }: { color: string; focused: boolean }) => (
          <SaveIcon width={28} height={28} isActive={focused}/>
        ),
        style: {
          backgroundColor: 'green',
        }
    },
    {
        name: "(my_subscriptions)/my_subscriptions",
        title: "Subscriptions",
        icon: ({ color, focused }: { color: string; focused: boolean }) => (
          <MySubscriptionsIcon width={28} height={28} isActive={focused}/>
        ),
        style: {
          backgroundColor: 'yellow',
        }
    }
]

export default function TabLayout() {
  
  const handleTabPress = () => {

  };

  return (
    <Tabs screenOptions={{ 
        tabBarActiveTintColor: 'white',
        headerShown: false,
        tabBarStyle: styles.tabBar,  
        tabBarHideOnKeyboard: true,
        tabBarBackground: () => <BlurView style={StyleSheet.absoluteFill} tint='dark' intensity={135}/>,
        tabBarButton: (props) => (
          <TouchableOpacity 
            {...props  as TouchableOpacityProps} 
            style={styles.tabBarButton}
          />
        )
       }}>
      {TAB_SCREENS.map((screen) => (
        <Tabs.Screen
            key={screen.name}
            name={screen.name}
            options={{
                title: screen.title,
                tabBarIcon: screen.icon,
            }}
        />
      ))}
    </Tabs>
  );
};

const styles = StyleSheet.create({
  tabBar:{
    position: 'absolute',
    borderTopWidth: 0,
    elevation: 0,
    height: 69,
    paddingTop: 0,
    paddingBottom: 30,
    backgroundColor: 'transparent',
  },
  tabBarButton:{
    flex: 1,
    // justifyContent: "center",
    alignItems: "center",
    paddingVertical: 10,
  },
  blurView: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    height: 84,
  }
});