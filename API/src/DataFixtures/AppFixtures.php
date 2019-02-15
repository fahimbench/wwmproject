<?php

namespace App\DataFixtures;

use App\Entity\User;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Common\Persistence\ObjectManager;
use Faker\Factory as faker;
use DateTime;
use App\Entity\Game;


class AppFixtures extends Fixture
{
    public function load(ObjectManager $manager)
    {
        $faker = faker::create("FR_fr");

        for($i = 0; $i < 100; $i++){
        $user = new User();
        
        $user->setPseudo($faker->userName)
             ->setEmail($faker->email)
             ->setFirstname($faker->firstName)
             ->setLastname($faker->lastName)
             ->setPassword($faker->password());

             

             
                $game = new Game();
                $game->setScore($faker->numberBetween(300,7200))
                     ->setType($faker->numberBetween(1,2))
                     ->setIdUser($user);
        
                     $manager->persist($game);
                
                $manager->persist($user);
        }

        

             $manager->flush();
    }


}
