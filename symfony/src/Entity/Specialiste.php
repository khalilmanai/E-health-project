<?php

namespace App\Entity;

use App\Repository\SpecialisteRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: SpecialisteRepository::class)]
class Specialiste extends User
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]

    public function __construct()
    {
    }
}
