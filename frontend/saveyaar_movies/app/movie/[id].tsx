import { useLocalSearchParams } from "expo-router";
import React, { useEffect, useState } from "react";
import { StyleSheet, View, Image, Text } from "react-native";
import { useSafeAreaInsets } from "react-native-safe-area-context";

import { BellIconPurple, DotIconPurple, HeartIconPurple, ShareIconPurple } from "@/assets/svg";
import TextComponent from "@/components/TextComponents";
import { IMAGE_URL, MOVIE_URL, TMDB_KEY } from "@env";
import Scale from "@/constants/Scale";
import { LinearGradient } from "expo-linear-gradient";
import GenreComponent from "@/components/GenreComponent";

interface TwoLineText {
    textTop: string,
    textBottom: string
};

interface Certification {
    certification: string
};

const TwoLineText = ({textTop, textBottom}: TwoLineText) => {
    return (
        <View style={styles.two_line_container}>
            <TextComponent
                text={textTop}
                size={8}
                numberOfLine={1}
                color="#FFFFFF"
            />
            <TextComponent
                text={textBottom}
                size={12}
                numberOfLine={1}
                color="#FFFFFF"
            />
        </View>
    )
};

const Certification = ({certification}: Certification) => {
    const [textWidth, setTextWidth] = useState(0);
    
    return (
        <>
            <Text
                style={styles.hiddenText}
                onLayout={(event) =>
                    setTextWidth(event.nativeEvent.layout.width)
                }
            >
                {/* This is not visible it just there to calculate textWidth */}
                {certification}
            </Text>
            <View style={[styles.certification, {width: textWidth+10}]}>
                <TextComponent
                    text={certification}
                    size={7.2}
                    color="#FFFFFF"
                    numberOfLine={1}
                />
            </View>
        </>
    )
};

export default function MovieScreen() {
    const { id } = useLocalSearchParams();

    const [movie, setMovie] = useState<any>(null);
    const [imageData, setImageData] = useState<string | null>(null);
    const [duration, setDuration] = useState<string | null>(null);
    const [languages, setLanguages] = useState<any[] | null>(null);
    const [rating, setRating] = useState<string | null>(null);
    const [genreList, setGenreList] = useState<string[] | null>(null);
    const [certifications, setCertifications] = useState<Set<string> | null>(null);

    useEffect(()=> {
        const fetchImage = async (backdrop_path: string) => {
            try {
                const response = await fetch(IMAGE_URL+backdrop_path, {
                  headers: {
                    Authorization: `Bearer ${TMDB_KEY}`,
                  },
                });
                const blob = await response.blob();
                const reader = new FileReader();

                reader.onloadend = () => {
                    if (typeof reader.result === 'string') {
                        setImageData(reader.result);
                    } else {
                        console.error('Expected string from FileReader, got:', typeof reader.result);
                    }
                };
                
                reader.readAsDataURL(blob);
            } catch (error) {
                console.error('Error fetching image:', error);
            }
        };

        const calc_runtime = (runtime: number) => {
            const hours = Math.floor(runtime / 60);
            const minutes = runtime % 60;
            
            if(hours > 0 && minutes > 0){
                return `${hours}h ${minutes}min`;
            } else if(hours > 0) {
                return `${hours}h`
            } else if(minutes > 0) {
                return `${minutes}min`
            } else {
                return 'N/A'
            }
        };

        const filter_indian_languages = (languages: any[]) => {
            let filtered_langues: any[] = [];
            languages.forEach(language => {
                if(language.iso_3166_1 === 'IN'){
                    filtered_langues.push(language);
                }
            });

            return filtered_langues;
        };

        const get_genres = (genre_list: any[]) => {
            const the_list: string[] = [];
            genre_list.forEach(genre => (
                the_list.push(genre.name)
            ));

            return the_list;
        };

        const get_certifications = (country: string, release_dates: any[]) => {
            let cert: Set<string> = new Set(); 
            release_dates.forEach(item => {
                if(item.iso_3166_1 === country) {
                    const inner_dates = item.release_dates;
                    inner_dates.forEach((item1: { certification: string; }) => {
                        if(item1.certification !== "")
                            cert.add(item1.certification);
                    })
                }
            });

            return cert;
        };

        const fetchMovie = async () => {
            const url = new URL(`${MOVIE_URL}/${id}`);
            console.log(url.toString());

            const response = await fetch(url.toString());

            if(response.status === 200) {
                const movieData = await response.json();
                setMovie(movieData);
                setDuration(calc_runtime(movieData.runtime | 0));
                setLanguages(filter_indian_languages(movieData.translations.translations));
                // setRating(movieData.rating.toString());
                setGenreList(get_genres(movieData.genres));
                setCertifications(get_certifications("IN", movieData.release_dates.results)); // Make the country variable later
                fetchImage(movieData.backdrop_path);
            } else {
                console.error(`Error: Received status code ${response.status} when trying to access movie id:${id}`);
            }
        };
        
        fetchMovie();
    }, [id]);

    const insets = useSafeAreaInsets();

    return (
        <View style={[styles.container, {paddingTop: insets.top+5}]}>
            {movie ? (
                <View>
                    {imageData && 
                        <View style={styles.image_container}>
                            <Image
                                source={{ uri: imageData }}
                                style={styles.backdrop}
                            />
                        </View>
                    }
                    <View style={styles.title}>
                        <TextComponent
                            text={movie.title}
                            size={16}
                            numberOfLine={2}
                            bold                
                        />
                        <View style={styles.icons}>
                            <BellIconPurple />
                            <HeartIconPurple />
                            <ShareIconPurple />
                        </View>
                    </View>
                    <View style={styles.color_content}>
                        <LinearGradient
                            colors={["#401D85", "#3D103D"]}
                            start={{ x: 0, y: 0 }}
                            end={{ x: 1, y: 0 }}
                            style={[StyleSheet.absoluteFill, {borderRadius: 8}]}
                        />
                        <TwoLineText textTop="Rating" textBottom={rating? rating : 'N/A'} />
                        <TwoLineText textTop="Release date" textBottom={movie.release_date || 'N/A'} />
                        <TwoLineText textTop="Duration" textBottom={duration || 'N/A'} />
                        <TwoLineText textTop="Languages" textBottom={languages?.length.toString() || 'N/A'} />
                    </View>
                    <View style={styles.ott}>
                        <DotIconPurple />
                        <TextComponent
                            text="Available on"
                            size={12}
                            style={{paddingLeft: 10}}
                        />
                    </View>
                    <TextComponent
                        text={movie.overview}
                        numberOfLine={10}
                        style={{paddingVertical: 10}}
                        color="#FFFFFF99"
                    />
                    {genreList && 
                        <GenreComponent genre_list={genreList} />
                    }
                    {certifications && 
                        Array.from(certifications).map((cert) => (
                            <Certification certification={cert} key={cert}/>
                        ))
                    }
                </View>
            ) : (
                <TextComponent text="Loading..." size={16} />
            )}
        </View>        
    )
};


const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#151515",
        paddingHorizontal: 20,
    },
    title: {
        flexDirection: 'row',
        alignContent: 'center',
        justifyContent: 'space-between',
        paddingVertical: 20,
    },
    icons: {
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'space-between',
        width: 96,
    },
    backdrop: {
        borderRadius: 14,
        width: Scale(380),
        height: 200
    },
    image_container: {
        alignItems: 'center',
    },
    color_content: {
        height: Scale(67),
        justifyContent: 'space-evenly',
        alignItems: 'center',
        flexDirection: 'row'
    },
    ott: {
        flexDirection: 'row',
        paddingTop: 20,
        paddingBottom: 10,
        alignItems: 'center',
        alignContent: 'center',
    },
    two_line_container: {
        flexDirection: 'column',
        alignItems: 'center'
    },
    certification: {
        backgroundColor: "#FFFFFF29",
        borderRadius: 4,
        height: 15,
        justifyContent: 'center',
        alignItems: 'center',
        marginTop: 4,
    },
    hiddenText: {
        position: 'absolute',
        opacity: 0,
        fontSize: 7.2,
    },
});